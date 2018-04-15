package com.aybits.hms.api.func.employee.beans;

public enum Role{

        MASTER_ADMIN(1),
        SUPER_ADMIN(2),
        SUPERVISOR(3),
        CSR(4);

        private int level;

        Role(int level){
            this.level=level;
        }

}
