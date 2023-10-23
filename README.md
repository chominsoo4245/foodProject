
# foodProject

이 프로젝트는 Naver API를 활용하여 위치와 음식에 대한 검색을 제공하며, Swagger API 및 RestController를 통해 API를 쉽게 사용할 수 있도록 구현되었습니다.

## Naver API 활용

Naver API는 위치와 음식에 대한 검색을 제공합니다. 이 프로젝트에서는 Naver API를 사용하여 사용자가 찾고자 하는 맛집 정보를 제공합니다. 

## Swagger API 및 RestController 활용

Swagger API는 API 문서화를 위한 강력한 도구입니다. 이 프로젝트에서는 Swagger API를 사용하여 API를 문서화하고 사용자가 쉽게 이해하고 사용할 수 있도록 하였습니다. RestController는 Spring 프레임워크에서 제공하는 기능으로, RESTful 웹 서비스를 쉽게 구축할 수 있도록 도와줍니다.

## 사용법 

application.yaml 파일을 수정하여 Naver API를 사용하기 위한 인증 정보를 추가해야 합니다. 
아래는 application.yaml 파일에 추가한 예시입니다:

```yaml
naver:
  url:
    search:
      local: https://openapi.naver.com/v1/search/local.json
      image: https://openapi.naver.com/v1/search/image
  client:
    id: YOUR_NAVER_CLIENT_ID
    secret: YOUR_NAVER_CLIENT_SECRET
```

### search Request And Response

```json
{
  "index": null,
  "title": "서민식당",
  "category": "한식>육류,고기요리",
  "address": "경상북도 경주시 천군동 149-3",
  "roadAddress": "경상북도 경주시 경감로 633",
  "homePageLink": "https://seominrestaurant.modoo.at/",
  "imageLink": "https://media.triple.guide/triple-cms/c_limit,f_auto,h_1024,w_1024/8bdd0d17-2f57-44b7-b58c-0b8264c2ffe4.jpeg",
  "visitCount": 0,
  "lastVisitDate": null,
  "visit": false
}
```
