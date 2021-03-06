## Git について

#### Git とは

プログラムソースの変更履歴を管理するバージョン管理ツール。

プロジェクトメンバー間で分担したソースコードをサーバ上でマージし一元管理する、また、ローカルでの作業だけでは作業者 PC にトラブルがあった際にソースコード消失のリスクがあり、それを回避する目的もある。

* ソースコードだけでなく、テキストや画像など、たいていのファイルは管理に含めることができる。

* 似たようなバージョン管理ツールに、メジャーどころでは Subversion / TeamFoundationServer など。

* Git そのものにも Github / Gitlab / GitBucket など、それぞれ特徴の異なるサービスがある。



---

#### Github

CUI （コマンドライン）での操作とサードパーティの GUI ツール（Tortoise Git、SourceTree など ）を使って操作する方法がある。

各用語など。

* リポジトリとブランチ。
  * リポジトリ：プロジェクト単位のリソースを管理する保管庫。
  * ブランチ　：ある時点から枝分かれしていく変更履歴の単位。
* 主要な各操作。
  * CLONE：リモートリポジトリをローカルに複製する。
  * COMMIT：その時点の修正内容を作業ブランチに確定する。
  * PUSH：作業ブランチのローカルリポジトリの内容をリモートリポジトリに反映する。
  * PULL：作業ブランチのリモートリポジトリの内容をローカルリポジトリに反映する。
  * FETCH：リモートリポジトリの履歴をローカルリポジトリに読み込む。
* リモートとローカルの考え方。
  * プロジェクトチーム内で運用する場合は必ずリモートを元にして、各個人のローカルでブランチを更新し、リモートに反映していく。
  * 個人開発など、小規模の場合はリモートリポジトリを用意せずにローカルリポジトリのみで履歴管理として運用する使い方もある。



ここでは Tortoise Git （通称 亀 Git ）での方法を記載。

----

#### 初期設定から CLONE まで

1. Tortoise Git インストール。

   https://tortoisegit.org/

   Language Packs の日本語もインストール。

2. インストール完了後、設定から言語を日本語に設定。

3. Github サインアップ

   * UserName: 任意

   * Email: SEP メールアドレス

   * パスワード: 任意

4. 登録後、サインイン

5. リポジトリのオーナー（ sep-dev なら松金）がリモートリポジトリ側の設定を行う

   * リポジトリの作成、もしくは更新（メンバーの設定）。

   * 対象のリポジトリのメンバーに招待する。
リポジトリ -> Settings -> Collaborators
     メールアドレスで対象ユーザを検索して Add

   * 対象ユーザに Invite メールが送信されるので、受信したユーザは Accept 。

6. PuTTYgen インストール
https://www.puttygen.com/
  
7. PuTTYgen を用いて SSH キーを作成・登録
https://qiita.com/amama-nagigi/items/4a1122778aaeea6b84de
  
* 加えて、PuTTYgen で Save private key で秘密カギを保存する
  
8. Invite 済リポジトリの SSH URL をコピー。
リポジトリ TOP -> 「 Clone or Download 」
  

git@github.com:sep-dev/xxx.git

9. リポジトリを CLONE したいディレクトリ上で右クリック -> 「 Git クローン」

10. 「URL」 に項番 8. の URL を入力。

11. 「Putty 認証キーのロード」で項番 7. の秘密鍵（ ppk ）を選択。

12. 「OK」で対象リポジトリが CLONE される。



----

#### COMMIT 、PUSH

* ローカルのソースコードをサーバに反映するというプロセス。

* ビルドが通らない状態のソースコードを PUSH しないこと。同一ブランチを扱っている他の作業者がそのコミットを PULL すると、ビルドエラーで作業が中断してしまう。

  

1. クローン済のフォルダ内でファイルを更新する。

   エクスプローラ上でのファイルアイコンのオーバーレイについて。

   * 変更されていないファイルは緑色のチェック
   * 変更されたファイルは赤色の！マーク
   * ブランチに追加されていないファイルはオーバーレイ無し。

2. 右クリックで「Git コミット -> "master" 」を選択。

   * 出てくるペインの「変更した項目」欄に更新したファイルの一覧が表示される。
     COMMIT 済みファイルであれば、一覧上でダブルクリックすることで最新 COMMIT 時点との差分が表示される。COMMIT の際は必ず差分を確認すること。
     COMMIT されていないファイルであれば、状態：管理外と表示される。

   * COMMIT したいファイルをチェックのうえ、コミットコメントを入力。

   * 「コミット」でブランチに更新が COMMIT される。

3. その後の画面で「プッシュ」を押下すると、秘密鍵を使用して選択中のブランチの更新がリモートリポジトリに反映。

   * この時点で PUSH しないのであれば、画面を閉じてこの操作は終了。
   * ローカルの COMMIT にとどめて作業を進め、ある時点で PUSH する場合は、右クリックメニュー「TortoiseGit」→「プッシュ」で PUSH を行う。 



----

#### FETCH、PULL

* 基本的には、FETCH してから PULL。
* PULL した際に同一ブランチ上での別々の更新がバッティングすると CONFLICT（衝突）が起きる。MERGE をして衝突を解消する必要がある。



----

#### BRANCH について

* リポジトリを展開しているフォルダ上で右クリック -> 「TortoiseGit 」
  * 既存のリモートブランチに切り替える場合「切り替え」
    * 新しいブランチを切ることもできる。
  * リモートもしくはローカルブランチを元に新しいブランチを切る場合「ブランチを作成」



* ブランチ運用の例。

  * master 

    * リリースブランチ。本番と同じソース状態を維持する。

    * リリース時に Develop ブランチを Master にマージ。

    * 基本的に、ローカルからは直接コミット・プッシュせず、プルリクエスト（Github）やマージリクエスト（Gitlab）をして差分をレビューしたうえでマージする。

  * develop

    * 開発ブランチ。開発環境（ステージング）と同じソース状態を維持する。

    * 各修正ブランチをここにマージしていく。リリース時にこのブランチをMaster へマージ。
    * master 同様、ローカルからは直接コミットせずにマジリクやプルリクをして差分レビューをしたうえでマージ。

  * feature/xxxx

    * 修正ブランチ。
* 運用方法により、簡潔な修正内容や、Redmineであればチケット番号などを xxxx にあたる部分に記載し各開発者が実施する修正・対応レベルで切るブランチ。
    * 大抵はDevelop ブランチの最新コミットを元に切る。ローカルからPUSHし、対応が一区切りして開発環境に反映する段階で develop ブランチへマージ。

  

Githubの場合、リモートリポジトリ Web コンソール上 Insights -> Network でコミットを時系列グラフで参照できる。

  ![image-20190903102254527](/Users/matsukane_eisuke/Library/Application Support/typora-user-images/image-20190903102254527.png)

Tortoise Git 上では、右クリックメニュー「 TortoiseGit 」→「ログを表示」で上記のリモートリポジトリ上のコミット時系列グラフと同様（ただしローカルのログ）を参照できる。

FETCH することでリモートリポジトリの最新ログに更新される。

  ![image-20190903103319681](/Users/matsukane_eisuke/Library/Application Support/typora-user-images/image-20190903103319681.png)

また、ログ上でコミットを選択することでそのコミットに含まれるファイルの一覧が下部に表示される。ファイルをダブルクリックすることで、コミットされた差分を参照できる。