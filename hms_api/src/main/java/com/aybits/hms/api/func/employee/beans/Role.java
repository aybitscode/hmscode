package com.aybits.hms.api.func.employee.beans;

<<<<<<< HEAD
=======
import com.fasterxml.jackson.annotation.JsonProperty;

>>>>>>> adil_develop
public enum Role{

        MASTER_ADMIN(1),
        SUPER_ADMIN(2),
        SUPERVISOR(3),
        CSR(4);

<<<<<<< HEAD
        private int level;
=======
        @JsonProperty("role_level")
        private final int level;
>>>>>>> adil_develop

        Role(int level){
            this.level=level;
        }

<<<<<<< HEAD
=======
        public int getRoleAsInt() {
                return level;
        }

        public String getRoleAsString() {
                return String.valueOf(level);
        }

        public static Role convertIntToRole(int iRole) {
                for (Role level : Role.values()) {
                        if (level.getRoleAsInt() == iRole) {
                                return level;
                        }
                }
                return null;
        }

        public static Role convertStringToRole(String inputRole) {
                for (Role level : Role.values()) {
                        if (level.getRoleAsString().equals(inputRole)) {
                                return level;
                        }
                }
                return null;
        }

        public static int convertRoleToInt(Role inputRole) {
                for (Role level : Role.values()) {
                        if (level.getRoleAsInt() == inputRole.getRoleAsInt()) {
                                return level.getRoleAsInt();
                        }
                }
                return -1;
        }

        public static String convertRoleToString(Role inputRole) {
                for (Role level : Role.values()) {
                        if (level.getRoleAsInt() == inputRole.getRoleAsInt()) {
                                return level.getRoleAsString();
                        }
                }
                return null;
        }

>>>>>>> adil_develop
}
