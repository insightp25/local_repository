# Introduction

### IntelliJ

* cmd + F9: 빌드. view file 고친 후 build만 하면 애플리케이션 껐다가 안 켜도 view가 refresh 되고 변경 코드가 반영된다.


### Git

* git alias 기능 존재
* 기타 예
    * ```sh
git add .
git stash # 적용된 commit이후로 변경된 모든 사항들 저장
stash 공간으로 이동
git stash pop # stash한 변경사항 다시 적용하기 # apply + drop # 새 브랜치를 생성하여 pop # 충돌사항이 있는 상황 등에 유용
git stash -p (hunk) # 원하는 것만 stash 하기(hunk: git에서 하나의 변경사항을 담은 단위)
git stash -m "다음 스태시하는 이유"
git stash branch "브랜치명" # 새로운 branch에 stash 적용하기
git stash apply # 치워둔 마지막 항목(번호 없을 시) 적용. 끝에 번호로 항목 지정 가능.
git stash drop # 치워둔 마지막 항목(번호 없을 시) # 삭제 끝에 번호로 항목 지정 가능

git stash list
git stash apply

# 특정 커밋코드로 checkout할 경우 메시지
git checkout 6aa730f
M	.idea/gradle.xml
M	.idea/misc.xml
M	build.gradle.kts
Note: switching to '6aa730f'.

You are in 'detached HEAD' state. You can look around, make experimental
changes and commit them, and you can discard any commits you make in this
state without impacting any branches by switching back to a branch.

If you want to create a new branch to retain commits you create, you may
do so (now or later) by using -c with the switch command. Example:

  git switch -c <new-branch-name>

Or undo this operation with:

  git switch -

Turn off this advice by setting config variable advice.detachedHead to false

HEAD is now at 6aa730f service 동시성 제어
```






<br>

