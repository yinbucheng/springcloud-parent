# 采用普通配置，放弃使用apollo
# 添加mybatis使用
# 多模块打包
## 1.将父pom添加  <packaging>pom</packaging>
## 2.指定子模块的启动类
 <!-- 指定main方法-->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <configuration>
                    <archive>
                        <manifest>
                            <addClasspath>true</addClasspath>
                            <mainClass>cn.intellif.server.zipkin.ServerZipkinApplication</mainClass>
                            <classpathPrefix>lib</classpathPrefix>
                            <useUniqueVersions>false</useUniqueVersions>
                        </manifest>
                        <manifestEntries>
                            <Class-Path>lib/</Class-Path>
                        </manifestEntries>
                    </archive>
                </configuration>
            </plugin>