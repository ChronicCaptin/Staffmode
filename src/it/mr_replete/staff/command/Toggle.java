package it.mr_replete.staff.command;

import it.mr_replete.staff.staff.Staffer;

public abstract class Toggle extends GCommand {

    public Toggle(String label, boolean user) {
        super(label, user);
    }

    public Toggle(String label, boolean user, String perm) {
        super(label,user,perm);
    }

    public Toggle(String label, boolean console, char key) {
        super(label,console,key);
    }

    protected abstract void toggle(Staffer player);

}
