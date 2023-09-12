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

# 2. Redis 사용

## Lettuce

- 구현이 간단하다
- spring data redis 를 이용하면 lettuce가 기본이기 때문에 별도의 라이브러리를 사용하지 않아도 된다.
- spin lock 방식이기 때문에 동시에 많은 쓰레드가 lock 획득 대기 상태라면 reids에 부하가 갈 수 있다.

## Redisson

- 락 획득 재시도를 기본으로 제공한다.
- pub-sub 방식으로 구현이 되어있기 때문에 lettuce와 비교했을 때 redis에 부하가 덜 간다.
- lock을 라이브러리 지원에서 제공해주기 때문에 사용법을 공부해야한다.

### 재시도가 필요하지 않은 lock -> lettuce 사용
### 재시도가 필요한 경우 -> redisson 사용







