/*
 * Authors: Steven Nguyen and Luke Norman
 * Date: 07/04/25
 * Collaborators: None
 * Sources: None
 */
public enum Setting {
    OFF("---"),
    LOWHEAT("--+"),
    MEDIUMHEAT("-++"),
    HIGHHEAT("+++");

    private final String setting;

    Setting(String setting) {
        this.setting = setting;
    }

    public String toString() {
        return '[' + setting + ']';
    }
}