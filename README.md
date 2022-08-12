# Soptionssix-AOS
앱 이름이 뭐에요~?

## *Code Convention*
[Code Convention](https://azure-sawfish-822.notion.site/Code-convention-12ec748d180745238dea21850538cd72)

<br>

## *Git Convention*
### Branch Strategy
### Git Flow

기본적으로 Git Flow 전략을 이용한다. 작업 시작 시 선행되어야 할 작업은 다음과 같다.

```
1. Issue를 생성한다.
2. feature Branch를 생성한다.
3. Add - Commit - Push - Pull Request 의 과정을 거친다.
4. Pull Request가 작성되면 작성자 이외의 다른 팀원이 Code Review를 한다.
5. Code Review가 완료되면 Pull Request 작성자가 develop Branch로 merge 한다.
6. merge된 작업이 있을 경우, 다른 브랜치에서 작업을 진행 중이던 개발자는 본인의 브랜치로 merge된 작업을 Pull 받아온다.
7. 종료된 Issue와 Pull Request의 Label과 Project를 관리한다.
```

* 참고 <br>
[Git Convention](https://azure-sawfish-822.notion.site/Git-Convention-7fe068db8e23471c980577c61f096b78)
