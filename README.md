# stock_concurrency

## 문제상황 : 다중 쓰레드가 자원을 사용할 때 (stock quantity를 감소시킬 때) race condition이 발생할 수 있다.

### @Synchronized 활용 : springBoot의 @Transactional annotation에서 db 트랜잭션이 완료되지 않았는데 멀티 프로세스 환경에서 resource 를 가져와 race condition이 발생할 수 있다. 

# 1.DB 활용

## Pessimistic Lock

### 장점 : 
- 테이블이나 row 단위로 lock을 걸어 데이터 정합성을 보장
- race condition이 빈번하게 발생하면 optimistic lock 보다 효과적일 수 있다.

### 단점 : 
- 성능 감소가 일어날 수 있다.


## Optimistic Lock

DB Lock을 이용하지 않고 버전을 다르게 함으로써 정합성을 맞추는 방법. -> 버전이 다를 경우 업데이트가 실패하기 때문에 실패시 로직을 추가해줘야함. 

### 장점 : 
- 직접 lock을 걸지 않으므로 성능상 좋을 수 있다.

### 단점 : 
- 개발자가 직접 버전이 다를 경우 실패 로직을 작성해 줘야 한다.


## 충돌이 잦을 경우 pessimistic Lock 사용 아닐 경우 Optimistic Lock


## Named Lock

이름을 가진 Meta Data Lock 이름을 가진 lock을 획득한 후 해제할 때 까지 다른 세션은 이 lock을 획득할 수 없다 .
선점시간이 끝나거나 별도의 로직을 작성해야 lock을 해제할 수 있다. 



