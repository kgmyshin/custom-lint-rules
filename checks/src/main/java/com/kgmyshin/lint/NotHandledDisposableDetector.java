package com.kgmyshin.lint;

import com.android.tools.lint.client.api.UElementHandler;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Detector.UastScanner;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;

import org.jetbrains.uast.UElement;
import org.jetbrains.uast.UQualifiedReferenceExpression;

import java.util.Collections;
import java.util.List;

public class NotHandledDisposableDetector extends Detector implements UastScanner {
    /**
     * Issue describing the problem and pointing to the detector implementation
     */
    public static final Issue ISSUE = Issue.create(
            // ID: used in @SuppressLint warnings etc
            "NotHandledDisposable",

            // Title -- shown in the IDE's preference dialog, as category headers in the
            // Analysis results window, etc
            "Not Handled Disposable",

            // Full explanation of the issue; you can use some markdown markup such as
            // `monospace`, *italic*, and **bold**.
            "Disposable should be called dispose.",
            Category.CORRECTNESS,
            6,
            Severity.ERROR,
            new Implementation(
                    NotHandledDisposableDetector.class,
                    Scope.JAVA_FILE_SCOPE));

    @Override
    public List<Class<? extends UElement>> getApplicableUastTypes() {
        return Collections.singletonList(UQualifiedReferenceExpression.class);
    }

    @Override
    public UElementHandler createUastHandler(JavaContext context) {

        return new UElementHandler() {
            @Override
            public void visitQualifiedReferenceExpression(UQualifiedReferenceExpression node) {
                // kotlin
                if (node.getPsi() != null &&
                        node.getPsi().getContext() != null &&
                        node.getPsi().getContext().toString().equals("BLOCK") &&
                        node.getSelector().asRenderString().startsWith("subscribe(") &&
                        node.getSelector().getExpressionType() != null &&
                        node.getSelector().getExpressionType().getCanonicalText().equals("io.reactivex.disposables.Disposable")) {
                    context.report(ISSUE, node, context.getLocation(node),
                            "Should handle Disposable");
                    return;
                }

                // java
                if (node.getPsi() != null &&
                        node.getPsi().getContext() != null &&
                        node.getPsi().getContext().getContext() != null &&
                        node.getPsi().getContext().getContext().toString().equals("PsiCodeBlock") &&
                        node.getSelector().asRenderString().startsWith("subscribe(") &&
                        node.getSelector().getExpressionType() != null &&
                        node.getSelector().getExpressionType().getCanonicalText().equals("io.reactivex.disposables.Disposable")) {
                    context.report(ISSUE, node, context.getLocation(node),
                            "Should handle Disposable");
                }
            }
        };
    }
}
