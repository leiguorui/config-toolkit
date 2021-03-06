/**
 * Copyright 1999-2014 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dangdang.config.service.easyzk.demo.normal;

import java.io.IOException;

import com.dangdang.config.service.GeneralConfigGroup;
import com.dangdang.config.service.observer.IObserver;
import com.dangdang.config.service.zookeeper.ConfigLocalCache;
import com.dangdang.config.service.zookeeper.ZookeeperConfigGroup;
import com.dangdang.config.service.zookeeper.ZookeeperConfigProfile;
import com.google.common.base.Preconditions;

/**
 * @author <a href="mailto:wangyuxuan@dangdang.com">Yuxuan Wang</a>
 *
 */
public class WithoutSpring {

    public static void main(String[] args) {
        final String rootNode = "/projectx/modulex";
        ZookeeperConfigProfile configProfile = new ZookeeperConfigProfile("192.168.5.99:2181", rootNode, "1.0.0");
        ZookeeperConfigGroup propertyGroup1 = new ZookeeperConfigGroup(configProfile, "property-group1");

        System.out.println(propertyGroup1);

        // Listen changes
        propertyGroup1.register(new IObserver() {
            @Override
            public void notified(String data, String value) {
                // Some initialization
            }
        });

        String stringProperty = propertyGroup1.get("string_property_key");
        Preconditions.checkState("Config-Toolkit".equals(stringProperty));
        String intProperty = propertyGroup1.get("int_property_key");
        Preconditions.checkState(1123 == Integer.parseInt(intProperty));

        propertyGroup1.close();
    }

}
