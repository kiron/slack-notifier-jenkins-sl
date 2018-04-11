package org.gradiant.jenkins.slack

import hudson.tasks.test.AbstractTestResultAction
import hudson.model.Actionable
import org.jenkinsci.plugins.displayurlapi.DisplayURLProvider

@NonCPS
String getTestSummary() {
  def testResultAction = currentBuild.rawBuild.getAction(AbstractTestResultAction.class)
  def summary = ""

  if (testResultAction != null) {
    def total = testResultAction.getTotalCount()
    def failed = testResultAction.getFailCount()
    def skipped = testResultAction.getSkipCount()
    def url = DisplayURLProvider.get().getRunURL(run) + "/tests"
    summary = "Test results: (<${url}|Open>)\n\t"
    summary = summary + ("Passed: " + (total - failed - skipped))
    summary = summary + (", Failed: " + failed)
    summary = summary + (", Skipped: " + skipped)
  } else {
    summary = "No tests found"
  }
  return summary
}
