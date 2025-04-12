package taskManager.com.br.taskManager.enums;

public enum PriorityLevel {
    ALTA("Alta"),
    MEDIA("Média"),
    BAIXA("Baixa");

    private final String displayName;

    PriorityLevel(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
} 