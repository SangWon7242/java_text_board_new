package com.sbs.text.board;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    int lastArticleId = 0;

    Scanner sc = new Scanner(System.in);

    System.out.println("== 자바 텍스트 게시판 2025 ==");

    while (true) {
      System.out.print("명령) ");
      String cmd = sc.nextLine();

      if (cmd.equals("/usr/article/write")) {
        System.out.println("== 게시물 작성 ==");

        System.out.print("제목 : ");
        String subject = sc.nextLine();

        System.out.print("내용 : ");
        String content = sc.nextLine();
        int id = ++lastArticleId;

        Article article = new Article();
        article.id = id;
        article.subject = subject;
        article.content = content;

        System.out.printf("%d번 게시물이 등록되었습니다.\n", article.id);
      } else if (cmd.equals("exit")) {
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
}
