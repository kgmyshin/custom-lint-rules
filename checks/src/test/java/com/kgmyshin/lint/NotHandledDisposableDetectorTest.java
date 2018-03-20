package com.kgmyshin.lint;

import com.android.tools.lint.checks.infrastructure.LintDetectorTest;
import com.android.tools.lint.checks.infrastructure.TestLintTask;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Issue;

import org.intellij.lang.annotations.Language;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class NotHandledDisposableDetectorTest extends LintDetectorTest {

    private File ktAssetsDir = new File("checks/src/test/kt-assets");
    private File javaAssetsDir = new File("checks/src/test/java-assets");

    public void testKotlin() throws IOException {
        @Language("kotlin") String content = getContent(ktAssetsDir, "Main.kt");

        getKtLintTask(kotlin(content)).run().expect(
                "src/test/pkg/Main.kt:8: Error: Should handle Disposable [NotHandledDisposable]\n" +
                        "        Single.just(\"test\").subscribe()\n" +
                        "        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                        "1 errors, 0 warnings"
        );

    }

    public void testJava() throws IOException {
        @Language("JAVA") String content = getContent(javaAssetsDir, "Main.java");
        getJavaLintTask(java(content)).run().expect(
                "src/test/pkg/Main.java:8: Error: Should handle Disposable [NotHandledDisposable]\n" +
                        "        Single.just(\"test\").subscribe();\n" +
                        "        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                        "1 errors, 0 warnings"
        );
    }

    @Override
    protected Detector getDetector() {
        return new NotHandledDisposableDetector();
    }

    @Override
    protected List<Issue> getIssues() {
        return Collections.singletonList(NotHandledDisposableDetector.ISSUE);
    }

    private TestLintTask getJavaLintTask(TestFile targetFile) throws IOException {
        TestLintTask lint = lint();
        @Language("JAVA") String completable = getContent(javaAssetsDir, "shadow/Completable.java");
        @Language("JAVA") String disposable = getContent(javaAssetsDir, "shadow/Disposable.java");
        @Language("JAVA") String flowable = getContent(javaAssetsDir, "shadow/Flowable.java");
        @Language("JAVA") String maybe = getContent(javaAssetsDir, "shadow/Maybe.java");
        @Language("JAVA") String observable = getContent(javaAssetsDir, "shadow/Observable.java");
        @Language("JAVA") String single = getContent(javaAssetsDir, "shadow/Single.java");
        lint.files(
                java(completable),
                java(disposable),
                java(flowable),
                java(maybe),
                java(observable),
                java(single),
                targetFile
        );
        return lint;
    }

    private TestLintTask getKtLintTask(TestFile targetFile) throws IOException {
        TestLintTask lint = lint();
        @Language("kotlin") String completable = getContent(ktAssetsDir, "shadow/Completable.kt");
        @Language("kotlin") String compositeDisposable = getContent(ktAssetsDir, "shadow/CompositeDisposable.kt");
        @Language("kotlin") String disposable = getContent(ktAssetsDir, "shadow/Disposable.kt");
        @Language("kotlin") String flowable = getContent(ktAssetsDir, "shadow/Flowable.kt");
        @Language("kotlin") String maybe = getContent(ktAssetsDir, "shadow/Maybe.kt");
        @Language("kotlin") String observable = getContent(ktAssetsDir, "shadow/Observable.kt");
        @Language("kotlin") String single = getContent(ktAssetsDir, "shadow/Single.kt");
        lint.files(
                kotlin(completable),
                kotlin(compositeDisposable),
                kotlin(disposable),
                kotlin(flowable),
                kotlin(maybe),
                kotlin(observable),
                kotlin(single),
                targetFile
        );
        return lint;
    }

    private String getContent(
            File dir,
            String filename
    ) throws IOException {
        return new String(
                Files.readAllBytes(
                        Paths.get(
                                new File(dir, filename).toURI()
                        )
                )
        );
    }
}