/*
 * Copyright 2018 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tang.chinwe.lib_page;

public class Platform {
    public static final boolean DEPENDENCY_IMMERSION_BAR;
    public static final boolean DEPENDENCY_CONSTRAINT_LAYOUT;


    static {
        DEPENDENCY_IMMERSION_BAR = findClassByClassName("com.gyf.immersionbar.ImmersionBar");
        DEPENDENCY_CONSTRAINT_LAYOUT= findClassByClassName("android.support.constraint.ConstraintLayout");
    }

    private static boolean findClassByClassName(String className) {
        boolean hasDependency;
        try {
            Class.forName(className);
            hasDependency = true;
        } catch (ClassNotFoundException e) {
            hasDependency = false;
        }
        return hasDependency;
    }
}
