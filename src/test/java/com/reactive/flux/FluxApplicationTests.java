package com.reactive.flux;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;


class FluxApplicationTests {


	@Test
	void firstMono(){
		Mono.just("Santiago");
	}

	@Test
	void monoWithConsumer(){
		Mono.just("Santiago")
				.log()
				.subscribe(s -> System.out.println(s),
						(e) -> System.out.println(e.getMessage()),
						() -> System.out.println("Complete"));

	}

	@Test
	void monoWithDoOn(){
		Mono.just("Velasquez")
				.log()
				.doOnSubscribe(sub -> System.out.println("Subscribed " + sub))
				.doOnRequest(request -> System.out.println("Request " + request))
				.doOnSuccess(success -> System.out.println("Success " + success))
				.subscribe(System.out::println);
	}

	@Test
	void emptyCompleteConsumerMono(){
		Mono.empty()
				.log()
				.subscribe(System.out::println, null, () -> System.out.println("Done"));
	}

	@Test
	void errorRunTimeExceptionMono(){
		Mono.error(new RuntimeException())
				.log()
				.subscribe(null, (e) -> System.out.println("Error: "+e.getMessage()));
	}

	@Test
	void errorDoOnErrorMono(){
		Mono.error(new RuntimeException())
				.log()
				.doOnError((e) -> System.out.println("Error: "+e.getMessage()))
				.subscribe(System.out::println);
	}

	@Test
	void errorOnErrorResumeMono(){
		Mono.error(new Exception())
				.onErrorResume((e) -> {
					System.out.println("Caught: "+ e);
					return Mono.just("Backup");
				}).log().subscribe(System.out::println);
	}

}
