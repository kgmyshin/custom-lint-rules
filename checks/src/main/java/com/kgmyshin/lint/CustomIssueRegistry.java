package com.kgmyshin.lint;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;

import java.util.Collections;
import java.util.List;

/*
 * The list of issues that will be checked when running <code>lint</code>.
 */
public class CustomIssueRegistry extends IssueRegistry {

    @Override
    public int getApi() {
        return com.android.tools.lint.detector.api.ApiKt.CURRENT_API;
    }

    @Override
    public List<Issue> getIssues() {
        return Collections.singletonList(NotHandledDisposableDetector.ISSUE);
    }
}

