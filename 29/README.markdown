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
Java Groovy Clojure **Scala** PHP Python Ruby C# ...etc

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


デモ２：Mavenプロジェクトの作成
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

IntelliJの**Maven Project**というツールウィンドウから、各ライフサイクルの実行ができます。
[Mavenのライフサイクルについてはこちら](http://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#Lifecycle_Reference)

**package**を実行すると、targetディレクトリにjarが生成されます。
では実行してみます。

    $ java -jar ***.jar

　  
　  
　  
　  
　  
　  
　  

    Failed to load Main-Class manifest attribute from
    untitled3-1.0.jar
    

これを実行可能に、maven-jar-pluginでmainクラスを指定します。

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



Tipsまとめ
-----
設定画面を開くときは Ctrl+Alt+S
あれやりたいんだけどメニューどこ？ => Shift+Ctrl+A で操作を検索
ショートカットキーがわからん => Shift+Ctrl+A でkeymapを検索

あの型開きたいんだけど => Ctrl+N
クラス名の入力めんどくさいよ => CamelCaseの頭文字でおｋ
この識別子の定義が見たい => Ctrl+B
この識別子の型の定義が見たい => Shift+Ctrl+B
親の顔が見たい => Ctrl+U
実装を追いたい => Ctrl+Alt+B
型階層が見たい => 型をエディタで選択して Ctrl+H
参照を追いたい => 識別子をエディタで選択して Alt+F7（おや、Windowのようすが）

コードフォーマットしたい => Ctrl+Alt+L（おや、Ubuntuのようすが）
オーバーライドするメソッドを探す => Ctrl+O
実装するメソッドを探す => Ctrl+I
矩形選択 => Alt+ドラッグ（おや、Windowのようすが）
Column Mode => Alt+Shift+Insertで切り替え


参考情報
-----
[プロダクティブ・プログラマ -プログラマのための生産性向上術](http://amzn.to/dMDAqv)


