public class User {
    private String username;
    private String password;
    private String role; // "admin" or "user"
    private String lastLoginTime = "";

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public String getLastLoginTime() { return lastLoginTime; }
    public void setLastLoginTime(String time) { this.lastLoginTime = time; }
}
