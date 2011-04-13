rpscala ２９ : IntelliJ IDEAではじめるScalaでつくるWicketアプリケーションとREST API powered by Scalatra with Maven
========

自己紹介
---------
* あさいです
* コンシューマ向けWebサービス屋さんでバックエンドシステムを作ってます（インフラ屋さんではないです）
* 仕事で主にScalaを使ってます
* もともとはJavaプログラマです

今日のおはなし
----------
最近Scalaを書くのにIntelliJっていうツールを使ってるんですが、その使い方を解説して欲しいっていう要望を頂いたので、せっかくだから使い方の解説と合わせて、なにか形になるものをその場でつくりながら、フレームワークとかライブラリの紹介とかも出来たらいいなと思ってます。

では。

### タイムテーブル
全部で５８分。

1. このへんの説明 ３分
2. IntelliJの紹介 ５分
3. デモ１：IntelliJ + Scala環境構築 １０分
4. デモ２：Mavenプロジェクトの作成 １０分
5. デモ３：WicketでWebページ作成 １０分
6. デモ４：ScalatraでREST API作成 １０分
7. デモ５：サンプルアプリケーション作成 １０分

### おねがい
* 質問とかはその場でお願いします。あとから聞かれても思い出せません。
* ４月ですし、初心者向けを心がけます。わかりにくいことがあったらすぐに質問してください。
* モヒカンな人はじゃんじゃん斧投げてください。

というわけで、よろしくお願いします。

----

IntelliJの紹介 （あと５５分）
----------
正式名称：IntelliJ IDEA  
読み方：いんてりじぇー **あいであ** （いであじゃないよ！）

最新バージョンは１０．０．３  
Ver．１は２００１年１月にリリースされ、今年で１０周年  
チェコのプラハにあるJetBrainsという会社が作っているそうです  

Javaで作られたJava用のIDEですがJava以外の言語にも対応  
Java Groovy Clojure **Scala** PHP Python Ruby ...etc

### ２つのエディション
IntelliJ IDEAには２つのエディションがあります。  
http://www.jetbrains.com/idea/features/editions_comparison_matrix.html  
http://www.jetbrains.com/idea/buy/index.jsp

**Community Edition**
* 機能が限られたオープンソース版
* ScalaでWebアプリ作れます

**Ultimate Edition**
* フル機能の有償版
* OSS開発や教育目的での無償提供もあり

### 特徴
* [Code Inspection & Fixes](http://www.jetbrains.com/idea/features/code_inspection.html)
* [Groovy and Grails](http://www.jetbrains.com/idea/features/groovy_grails.html)
* [IntelliJ IDEA — Free IDE for Google Android](http://www.jetbrains.com/idea/features/google_android.html)
* [Google App Engine](http://www.jetbrains.com/idea/features/google_app_engine.html)
* [Version Control Systems Integration](http://www.jetbrains.com/idea/features/version_control.html)
* [Ant and Maven Integration](http://www.jetbrains.com/idea/features/ant_maven.html)

----

デモ１：IntelliJ + Scala環境構築 （あと５０分）
-----
### ダウンロード〜インストール
ダウンロードはこちらから。今回はCommunity Editionを使用。  
http://www.jetbrains.com/idea/download/index.html

インストール〜実行は、tar.gzを展開して、bin/idea.shを実行するだけ。  
事前にJDKのインストールと、環境変数JAVA_HOMEまたはIDEA_JDKの設定が必要です。

### Scala Pluginのインストール
Plugin ManagerのAvailableタブで **Scala** を検索し、表示された **Scala** を右クリックしてインストール。
***Scala Power Pack***はインストールしないよう注意！

### 細かい設定
IntelliJの設定画面を開くときは Ctrl+Alt+S  
プロジェクトの設定画面を開くときは F4  
JVMのパラメータは ./bin/idea.vmoptions  
個人設定の保存先とかの設定は ./bin/idea.properties


### サンプルプロジェクトの作成
IDEAのプロジェクト管理は、複数のモジュールを含むことのできる形式です。  
プロジェクト作るときに「Scalaプロジェクト！」みたいなものは無いです。  
モジュール作成時に、desired technologyとしてScalaを選択します。  
ScalaでMainを書いて実行、テストコードを追加してテストを実行してみます。


デモ２：Mavenプロジェクトの作成 （あと４０分）
-----
ビルドツールとしてMavenを使います。  
ScalaのビルドツールとしてはSBT(Simple Build Tool)がメジャーですが、IDEとの親和性を考慮すると、Mavenが使い勝手が良いです。

モジュール作成時に、Maven Moduleを選択します。  
モジュールごとにMavenの設定ファイルとしてpom.xmlが生成されます。

Scalaコードをビルドするためのプラグインと、依存ライブラリとしてScalaライブラリを追記します。

    <repositories>
      <repository>
        <id>scala-tools.org</id>
        <name>Scala-tools Maven2 Repository</name>
        <url>http://scala-tools.org/repo-releases</url>
      </repository>
    </repositories>

    <pluginRepositories>
      <pluginRepository>
        <id>scala-tools.org</id>
        <name>Scala-tools Maven2 Repository</name>
        <url>http://scala-tools.org/repo-releases</url>
      </pluginRepository>
    </pluginRepositories>

    <dependencies>
      <dependency>
        <groupId>org.scala-lang</groupId>
        <artifactId>scala-library</artifactId>
        <version>2.8.1</version>
      </dependency>
    </dependencies>

    <build>
      <plugins>
        <plugin>
          <groupId>org.scala-tools</groupId>
          <artifactId>maven-scala-plugin</artifactId>
          <executions>
            <execution>
              <goals>
                <goal>compile</goal>
                <goal>testCompile</goal>
              </goals>
            </execution>
          </executions>
          <configuration>
            <scalaVersion>2.8.1</scalaVersion>
          </configuration>
        </plugin>
      </plugins>
    </build>

IntelliJの**Maven Project**というツールウィンドウから、各ライフサイクル、プラグインの実行ができます。  
[Mavenのライフサイクルについてはこちら](http://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#Lifecycle_Reference)

### Mainを作って実行する
先ほどと同様にMainを作成します。

    object Main {
      def main(args: Array[String]) {
        println("Hello")
      }
    }

これをMavenから実行できるようにmaven-scala-pluginでMainクラスを指定します。

    <configuration>
      <scalaVersion>2.8.1</scalaVersion>
      <launchers>
        <launcher>
         <id>main</id>
         <mainClass>Main</mainClass>
        </launcher>
      </launchers>
    </configuration>

Maven ProjectからPlugin -> scala -> scala:runを実行すると、Mainクラスを実行できます。

他にも、maven-scala-pluginでは、**scala:cc**でfscを起動したり、**scala:console**でREPLを起動することもできます。


### jarを作る
**package**を実行すると、コンパイルとテストが行われた上で、targetディレクトリにjarが生成されます。
では実行してみます。

    $ java -jar ***.jar

　  
　  
　  
　  
　  
　  
　  

    Failed to load Main-Class manifest attribute from
    ***.jar

とまぁ、ManifestにMainクラスの指定がないので動きません。  
これを実行可能にするため、maven-jar-pluginでManifestを設定します。

    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-jar-plugin</artifactId>
      <configuration>
        <archive>
          <manifest>
            <mainClass>Main</mainClass>
          </manifest>
        </archive>
      </configuration>
    </plugin>

あらためて動かしてみます。

    $ java -jar ***.jar

　  
　  
　  
　  
　  
　  

    Exception in thread "main" java.lang.NoClassDefFoundError: scala/ScalaObject
	at java.lang.ClassLoader.defineClass1(Native Method)
	at java.lang.ClassLoader.defineClassCond(ClassLoader.java:632)
	at java.lang.ClassLoader.defineClass(ClassLoader.java:616)
	at java.security.SecureClassLoader.defineClass(SecureClassLoader.java:141)
	at java.net.URLClassLoader.defineClass(URLClassLoader.java:283)
	at java.net.URLClassLoader.access$000(URLClassLoader.java:58)
	at java.net.URLClassLoader$1.run(URLClassLoader.java:197)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.net.URLClassLoader.findClass(URLClassLoader.java:190)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:307)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:301)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:248)
	at Main.main(Main.scala)
    Caused by: java.lang.ClassNotFoundException: scala.ScalaObject
	at java.net.URLClassLoader$1.run(URLClassLoader.java:202)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.net.URLClassLoader.findClass(URLClassLoader.java:190)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:307)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:301)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:248)
	... 13 more

Mainは呼び出されましたが、依存ライブラリが見当たりません。  
これを解決するために、maven-dependency-pluginで依存ライブラリをコピーします。

    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-dependency-plugin</artifactId>
      <executions>
        <execution>
          <phase>package</phase>
          <goals>
            <goal>copy-dependencies</goal>
          </goals>
        </execution>
      </executions>
    </plugin>

さらに、maven-jar-pluginのManifestをいかのように変更します。


    <manifest>
      <mainClass>Main</mainClass>
      <addClasspath>true</addClasspath>
      <classpathPrefix>dependency/</classpathPrefix>
    </manifest>


これで動きます。

    $ java -jar ***.jar
    Hello

### おまけ：fatjarの作成
maven-shade-pluginを使うと、依存ライブラリを全部1つのjarにまとめることができます。

    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-shade-plugin</artifactId>
      <executions>
        <execution>
          <phase>package</phase>
          <goals>
            <goal>shade</goal>
          </goals>
        </execution>
      </executions>
    </plugin>


デモ３：WicketでWebページ作成 （あと３０分）
-----
続いて、このモジュールをWicketアプリケーションにします。  
まずは、pom.xmlのpackagingを変更します。


    <groupId>rpscala</groupId>
    <artifactId>rpscala29</artifactId>
    <packaging>war</packaging>
    <version>1.0</version>

maven-jar-pluginとmaven-shade-pluginは不要なので削除します。  
src/main/webapp/WEB-INF/web.xmlを以下のように空の内容で作成します。

    <?xml version="1.0" encoding="UTF-8"?>
    <web-app version="3.0" xmlns="http://java.sun.com/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd">
    </web-app>

これでpackageを実行するとwarが作成されます。

### jettyで実行可能に
jetty-maven-pluginを使用することで、mavenからjettyを起動することができます。

    <plugin>
      <groupId>org.mortbay.jetty</groupId>
      <artifactId>jetty-maven-plugin</artifactId>
      <version>7.2.2.v20101205</version>
    </plugin>

これだけ書いて、Plugins -> jetty -> jetty:run を実行すると、jettyが8080ポートで起動し、warをデプロイした状態になります。  
src/main/webapp/index.html を作成して動作を確認します。


### WicketFilterを入れる
まずはpom.xmlにWicketのdependencyを追加します。

    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>servlet-api</artifactId>
      <version>2.5</version>
    </dependency>


次にWicketのWebApplicationクラスを実装します。


# 資料の準備が間に合わなかったのでココから先はアドリブで！


Tipsまとめ
-----
* 設定画面を開くときは Ctrl+Alt+S
* あれやりたいんだけどメニューどこ？ => Shift+Ctrl+A で操作を検索
* ショートカットキーがわからん => Shift+Ctrl+A でkeymapを検索

* あの型開きたいんだけど => Ctrl+N
* クラス名の入力めんどくさいよ => CamelCaseの頭文字でおｋ
* この識別子の定義が見たい => Ctrl+B
* この識別子の型の定義が見たい => Shift+Ctrl+B
* 親の顔が見たい => Ctrl+U
* 実装を追いたい => Ctrl+Alt+B
* 型階層が見たい => 型をエディタで選択して Ctrl+H
* 参照を追いたい => 識別子をエディタで選択して Alt+F7（おや、Windowのようすが）

* コードフォーマットしたい => Ctrl+Alt+L（おや、Ubuntuのようすが）
* オーバーライドするメソッドを探す => Ctrl+O
* 実装するメソッドを探す => Ctrl+I
* 矩形選択 => Alt+ドラッグ（おや、Windowのようすが）
* Column Mode => Alt+Shift+Insertで切り替え


参考情報
-----
[プロダクティブ・プログラマ -プログラマのための生産性向上術](http://amzn.to/dMDAqv)


