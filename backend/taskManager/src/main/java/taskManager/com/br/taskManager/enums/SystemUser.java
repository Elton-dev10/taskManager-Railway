package taskManager.com.br.taskManager.enums;

public enum SystemUser {
    JOHN_DOE("John Doe"),
    JANE_SMITH("Jane Smith"),
    MIKE_JOHNSON("Mike Johnson"),
    SARAH_WILLIAMS("Sarah Williams");

    private final String displayName;

    SystemUser(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
} 