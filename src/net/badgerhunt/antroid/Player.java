package net.badgerhunt.antroid;

public class Player {
    public final String name;
    public final String status;
    public final Integer submitId;
    public final Integer userId;

    public Player(String name, String status, Integer submitId, Integer userId) {
        this.name = name;
        this.status = status;
        this.submitId = submitId;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (name != null ? !name.equals(player.name) : player.name != null) return false;
        if (status != null ? !status.equals(player.status) : player.status != null) return false;
        if (submitId != null ? !submitId.equals(player.submitId) : player.submitId != null) return false;
        if (userId != null ? !userId.equals(player.userId) : player.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (submitId != null ? submitId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", submitId=" + submitId +
                ", userId=" + userId +
                '}';
    }
}
