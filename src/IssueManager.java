import java.util.ArrayList;
import java.util.List;

public class IssueManager {

    private List<Issue> issues = new ArrayList<>();

    // ── Add ──────────────────────────────────────────────────
    public void addIssue(Issue issue) {
        issues.add(issue);
    }

    // ── Get by ID ────────────────────────────────────────────
    public Issue getById(int id) {
        for (Issue i : issues) {
            if (i.getId() == id) return i;
        }
        return null;
    }

    // ── All issues ───────────────────────────────────────────
    public List<Issue> getAllIssues() {
        return issues;
    }

    // ── Issues by citizen ────────────────────────────────────
    public List<Issue> getByUser(String username) {
        List<Issue> result = new ArrayList<>();
        for (Issue i : issues) {
            if (i.getReportedBy().equalsIgnoreCase(username)) {
                result.add(i);
            }
        }
        return result;
    }

    // ── Count by status (used for statistics) ────────────────
    private int countByStatus(String status) {
        int count = 0;
        for (Issue i : issues) {
            if (i.getStatus().equalsIgnoreCase(status)) count++;
        }
        return count;
    }

    // ── Count critical issues (used for statistics) ───────────
    private int countCritical() {
        int count = 0;
        for (Issue i : issues) {
            if (i.getPriority().equalsIgnoreCase("Critical")) count++;
        }
        return count;
    }

    // ── Update status ────────────────────────────────────────
    public boolean updateStatus(int id, String status) {
        Issue issue = getById(id);
        if (issue == null) return false;
        issue.setStatus(status);
        return true;
    }

    // ── Assign department ────────────────────────────────────
    public boolean assignDepartment(int id, String department) {
        Issue issue = getById(id);
        if (issue == null) return false;
        issue.setDepartment(department);
        return true;
    }

    // ── Update priority ──────────────────────────────────────
    public boolean updatePriority(int id, String priority) {
        Issue issue = getById(id);
        if (issue == null) return false;
        issue.setPriority(priority);
        return true;
    }

    // ── Delete issue ─────────────────────────────────────────
    public boolean deleteIssue(int id) {
        Issue issue = getById(id);
        if (issue == null) return false;
        issues.remove(issue);
        return true;
    }

    // ── Statistics ───────────────────────────────────────────
    public void printStatistics() {
        int total    = issues.size();
        int pending  = countByStatus("Pending");
        int progress = countByStatus("In Progress");
        int resolved = countByStatus("Resolved");
        int critical = countCritical();

        System.out.println("\n  ==============================");
        System.out.println("       ISSUE STATISTICS");
        System.out.println("  ==============================");
        System.out.printf ("  Total Issues   : %d%n", total);
        System.out.printf ("  Pending        : %d%n", pending);
        System.out.printf ("  In Progress    : %d%n", progress);
        System.out.printf ("  Resolved       : %d%n", resolved);
        System.out.printf ("  Critical Issues: %d%n", critical);
        System.out.println("  ==============================\n");
    }

    // ── Print a list of issues ───────────────────────────────
    public void printList(List<Issue> list) {
        if (list.isEmpty()) {
            System.out.println("\n  [!] No issues found.\n");
            return;
        }
        System.out.println();
        for (Issue i : list) i.printSummary();
    }
}