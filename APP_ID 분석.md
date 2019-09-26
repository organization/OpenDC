# app_id 분석

## 확인된 것

1. **Firebase**에서 사용하는 **google_app_id** + 앱 서명키임
 (따라서 앱 서명이 바뀌면 글을 정상적으로 조회할 수 **없음**)

## 해결 방법

1. app_id 키젠 제작
2. app_id 사용하는 API를 모두 [직접 제작한 RESTFul API 서버로 교체](https://github.com/organization/OpenDC-RESTFul-API-Server)
