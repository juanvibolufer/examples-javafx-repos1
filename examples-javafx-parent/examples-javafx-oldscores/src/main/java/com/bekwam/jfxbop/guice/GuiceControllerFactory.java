/*
 * Copyright 2015 Bekwam, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bekwam.jfxbop.guice;

import com.google.common.base.Preconditions;
import com.google.inject.Injector;
import javafx.util.Callback;

import javax.inject.Inject;

/**
 * Integration between JavaFX factory for controllers and Google Guice
 *
 * @author carl_000
 */
public class GuiceControllerFactory implements Callback<Class<?>, Object> {

    @Inject
    protected Injector injector;

    /**
     * Uses Google Guice to provide instances to the JavaFX factory
     *
     * @param param
     * @return
     */
    @Override
    public Object call(Class<?> param) {
        Preconditions.checkNotNull(injector);
        return injector.getInstance(param);
    }
}
