# algorithm
일주일에 세개(네개)씩 풉니다.
문제번호는 백준 기준입니다.

### 1주차 정렬
 - 1181번 
    - set 자료구조를 사용해서 중복 제거
    - 그냥 정렬
 - 2252번 줄세우기
    - 위상정렬을 사용
 - 1005번 
    - 위상정렬 + DP
    - 사실상 2252를 풀고 보니 다를 게 없었다
    
 - 1422번 
    - char 사용한건 별 쓸모없는 부분이다 비효율적으로 구현함... 그냥 숫자 그대로 비교해도 똑같음
    - 문자열의 길이가 다른 경우만 생각하면 된다
      - 더 긴 문자열에서, 넘어가는 부분과 짧은 문자열을 비교하는 것 반복
      - ex) 334, 33 의 경우
        - 33과 33을 먼저 비교한다(짧은 문자열의 길이만큼 우선 비교)
        - 33과 4를 비교한다 => 이 로직 반복
    - N - K 만큼 채우는 부분에서 조금 헤맸는데,
      - 어차피 N - K 만큼 가장 큰 값을 정렬할 리스트에 넣고 시작해도 결과는 똑같으므로, 넣고 시작함
