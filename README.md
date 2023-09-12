# stock_concurrency

## 문제상황 : 다중 쓰레드가 자원을 사용할 때 (stock quantity를 감소시킬 때) race condition이 발생할 수 있다.

### @Synchronized 활용 : springBoot의 @Transactional annotation에서 db 트랜잭션이 완료되지 않았는데 멀티 프로세스 환경에서 resource 를 가져와 race condition이 발생할 수 있다. 

1.DB 활용

## Pessimistic Lock

- 테이블이나 row 단위로 lock을 걸어 데이터 정합성을 보장
- race condition이 빈번하게 발생하면 optimistic lock 보다 효과적일 수 있다.
  

