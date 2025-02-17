package com.sbs.text.board;

import java.util.*;

public class Main {
  public static void main(String[] args) {
    List<Article> articles = new ArrayList<>();
    int lastArticleId = 0;

    Scanner sc = new Scanner(System.in);

    System.out.println("== 자바 텍스트 게시판 2025 ==");

    while (true) {
      System.out.print("명령) ");
      String cmd = sc.nextLine();

      Rq rq = new Rq(cmd);

      if (rq.getUrlPath().equals("/usr/article/write")) {
        System.out.println("== 게시물 작성 ==");

        System.out.print("제목 : ");
        String subject = sc.nextLine();

        System.out.print("내용 : ");
        String content = sc.nextLine();
        int id = ++lastArticleId;

        Article article = new Article(id, subject, content);
        articles.add(article);

        System.out.println("생성된 게시물 객체 : " + article);
        System.out.printf("%d번 게시물이 등록되었습니다.\n", article.id);
      } else if (rq.getUrlPath().equals("/usr/article/list")) {
        System.out.println("== 게시물 리스트 ==");

        if (articles.isEmpty()) {
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }

        for (int i = articles.size() - 1; i >= 0; i--) {
          Article article = articles.get(i);
          System.out.printf("%d / %s\n", article.id, article.subject);
        }

      } else if (rq.getUrlPath().equals("exit")) {
        break;
      } else {
        System.out.println("잘못 된 명령어입니다.");
      }
    }
    System.out.println("== 게시판 종료 ==");

    sc.close();
  }
}

class Article {
  int id;
  String subject;
  String content;

  public Article(int id, String subject, String content) {
    this.id = id;
    this.subject = subject;
    this.content = content;
  }
}

class Rq {
  String url;
  Map<String, String> params;
  String urlPath;

  Rq(String url) {
    this.url = url;
    this.params = Util.getParamsFromUrl(this.url);
    this.urlPath = Util.getPathFromUrl(this.url);
  }

  public Map<String, String> getParam() {
    return params;
  }

  public String getUrlPath() {
    return urlPath;
  }
}

class Util {

  public static Map<String, String> getParamsFromUrl(String url) {
    Map<String, String> params = new HashMap<>();
    String[] urlBits = url.split("\\?", 2);

    if (urlBits.length == 1) return params;

    String queryStr = urlBits[1];

    for (String bit : queryStr.split("&")) {
      String[] bits = bit.split("=", 2);

      if (bits.length == 1) {
        continue;
      }

      params.put(bits[0], bits[1]);
    }

    return params;
  }

  public static String getPathFromUrl(String url) {
    return url.split("\\?", 2)[0];
  }
}
