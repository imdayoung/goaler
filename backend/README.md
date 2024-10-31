# backend

## Commit Convention
| 타입      | 설명                                    |
|-----------|-----------------------------------------|
| feat      | 새로운 기능 추가                         |
| fix       | 버그 수정                                |
| refactor  | 코드 리팩토링                            |
| docs      | 문서 수정                                |
| style     | 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우 |
| chore     | 빌드 업무 수정, 패키지 매니저 수정       |

[예시] feat: 가계부 조회 기능 추가

## Branch Workflow
| 브랜치 타입      | 형식                      | 예시                           |
|-----------------|--------------------------|-------------------------------|
| 기능 브랜치      | `feature/기능-설명`        | `feature/login` |
| 버그 수정 브랜치 | `bugfix/버그-설명`         | `bugfix/fix-login-error`      |
| 핫픽스 브랜치    | `hotfix/문제-설명`         | `hotfix/security-patch`       |
| 릴리스 브랜치    | `release/버전번호`         | `release/1.0.0`               |
| 기타 브랜치      | `task/설명`               | `task/setup-ci`               |

**1. 브랜치 생성:** develop 브랜치에서 새로운 작업을 위한 브랜치를 생성 <br/>
**2. 작업 및 PR:** 생성한 브랜치에서 작업을 완료한 후, develop 브랜치로 Pull Request(PR) <br/>
**3. 리뷰 및 병합:** 리뷰어가 PR을 검토하고, 병합이 완료되면 작업 브랜치를 삭제 <br/>
