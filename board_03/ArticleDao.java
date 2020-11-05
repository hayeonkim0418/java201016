package board_03;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ArticleDao {
	private ArrayList<Article> articles;
	private int no = 4;

	public ArticleDao() {
		articles = new ArrayList<>();

		Article a1 = new Article(1, "�ȳ�1", "����1", 1, getCurrentDate(), 88);
		Article a2 = new Article(2, "�ݰ���2", "����2", 2, getCurrentDate(), 109);
		Article a3 = new Article(3, "�ȳ��ϼ���3", "����3",3, getCurrentDate(), 40);

		articles.add(a1);
		articles.add(a2);
		articles.add(a3);
	}

	public Article getArticleById(int targetId) {
		for (int i = 0; i < articles.size(); i++) {
			int id = articles.get(i).getId();
			if (id == targetId) {
				return articles.get(i);
			}
		}

		return null;
	}

	private static String getCurrentDate() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
		Date time = new Date();
		String time1 = format1.format(time);

		return time1;
	}

	public ArrayList<Article> getSearchByFlag(int flag, String keyword) {

		ArrayList<Article> searchArticle = new ArrayList<>();

		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			String str = article.getPropertiesByFlag(flag);

			if (str.contains(keyword)) {
				searchArticle.add(article);
			}
		}
		return searchArticle;
	}

//    public ArrayList<Article> getReadByFlag(int flag){
//    	ArrayList<Article> ReadArticle = new ArrayList<>();
//    	
//		return ReadArticle;
//    }

	public void removeArticle(Article a) {
		articles.remove(a);
	}

	public void insertArticle(Article a) {
		a.setId(no);
		no++;
		articles.add(a);
	}

	public ArrayList<Article> getArticles() {
		return articles;
	}
}
