/*
 * Copyright 2013 wada811<at.wada811@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package at.wada811.app;

import android.app.Application;
import at.wada811.security.PRNGFixes;
import java.security.SecureRandom;

/**
 * Fixes for the output of the default PRNG having low entropy.<br>
 * {@link PRNGFixes} makes {@link SecureRandom} more random.
 * 
 * @see <a href="http://android-developers.blogspot.jp/2013/08/some-securerandom-thoughts.html">Some
 *      SecureRandom Thoughts | Android Developers Blog</a>
 */
public class AppFixedPRNG extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        PRNGFixes.apply();
    }

}
