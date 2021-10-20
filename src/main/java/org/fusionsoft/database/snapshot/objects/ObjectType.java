/*
 * Copyright (C) 2018-2021 FusionSoft
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied.
 *
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.fusionsoft.database.snapshot.objects;

import org.cactoos.Text;

/**
 * The enum ObjectType.
 */
public enum ObjectType implements Text {
    /**
     * The db role objectType constant.
     */
    ROLE() {
        @Override
        public String asString() {
            return "role";
        }
    },
    /**
     * The db user objectType constant.
     */
    USER() {
        @Override
        public String asString() {
            return "user";
        }
    },
    /**
     * The db schema objectType constant.
     */
    SCHEMA() {
        @Override
        public String asString() {
            return "schema";
        }
    },
    /**
     * The db table objectType constant.
     */
    TABLE() {
        @Override
        public String asString() {
            return "table";
        }
    },
    /**
     * The db sequence objectType constant.
     */
    SEQUENCE() {
        @Override
        public String asString() {
            return "sequence";
        }
    },
    /**
     * The db tablespace objectType constant.
     */
    TABLESPACE() {
        @Override
        public String asString() {
            return "tablespace";
        }
    },
    /**
     * The db view objectType constant.
     */
    VIEW() {
        @Override
        public String asString() {
            return "view";
        }
    },
    /**
     * The db procedure objectType constant.
     */
    PROCEDURE() {
        @Override
        public String asString() {
            return "procedure";
        }
    },
    /**
     * The db function objectType constant.
     */
    FUNCTION() {
        @Override
        public String asString() {
            return "function";
        }
    },
    /**
     * The db trigger objectType constant.
     */
    TRIGGER() {
        @Override
        public String asString() {
            return "trigger";
        }
    },
    /**
     * The db tuple objectType constant.
     */
    UDT_TUPLE() {
        @Override
        public String asString() {
            return "tuple";
        }
    },
    /**
     * The db enum objectType constant.
     */
    UDT_ENUM() {
        @Override
        public String asString() {
            return "enum";
        }
    },
    /**
     * The db domain objectType constant.
     */
    UDT_DOMAIN() {
        @Override
        public String asString() {
            return "domain";
        }
    },
    /**
     * The db constraint objectType constant.
     */
    CONSTRAINT() {
        @Override
        public String asString() {
            return "constraint";
        }
    },
    /**
     * The db index objectType constant.
     */
    INDEX() {
        @Override
        public String asString() {
            return "index";
        }
    };

}
