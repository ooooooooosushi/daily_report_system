package actions;

import java.io.IOException;

import javax.servlet.ServletException;

import constants.AttributeConst;
import constants.ForwardConst;

/**
 * トップページに関する処理を行うActionクラス
 *
 */
public class TopAction extends ActionBase {

	/**
     * indexメソッドを実行する
     */
	@Override
	public void process() throws ServletException, IOException {

		// メソッド実行
		invoke();
	}

	/**
     * 一覧画面を表示する
     */
	public void index() throws ServletException, IOException {

		// セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションから削除
		String flush = getSessionScope(AttributeConst.FLUSH);
		if (flush != null) {
			putRequestScope(AttributeConst.FLUSH, flush);
			removeSessionScope(AttributeConst.FLUSH);
		}

		// 一覧画面を表示
		forward(ForwardConst.FW_TOP_INDEX);
	}

	/**
     * ログイン画面を表示する
     * @throws ServletException
     * @throws IOException
     */
	public void showLogin() throws ServletException, IOException {

		// CSRF対策用トークンを設定
		putRequestScope(AttributeConst.TOKEN, getTokenId());

		// セッションにフラッシュメッセージが登録されている場合はリクエストスコープに設定する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH,flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        // ログイン画面を表示
        forward(ForwardConst.FW_LOGIN);
	}
}
