package com.reactive.flux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@FunctionalInterface
interface Predicate<String> {
	boolean check(String string);
}
public class FluxApplication {

	public static void main(String[] args) {


		Predicate<String> myPredicate = (string) -> string.startsWith("S");
		Mono.just("Santiago").log()
				.doOnSuccess(System.out::println)
				.doOnRequest(System.out::println)
				.doOnError(System.out::println)
				.subscribe(myPredicate::check);

		Predicate<Integer> greaterThan50 = n -> n > 50;

		List<Integer> list1 = List.of(12,4,54,6,5,43,534);
		List<Integer> list2 = List.of(412,4,5,6,51,43,5);

		List<List<Integer>> listList = new ArrayList<>();
		listList.add(list1);
		listList.add(list2);

		listList.stream()
				.flatMap(list -> list.stream().filter(greaterThan50::check))
				.forEach(System.out::println);

//		Long result = fib(100);
//		System.out.println("Result: " + result);

		int offset = 1;
		Stream.iterate(new float[]{0, 1}, n -> new float[]{n[1], n[0] + n[1]})
				.limit(100 + offset)
				.map(t -> t[0])
				.forEach(n -> System.out.print(n + " "));


		long sum = Stream.iterate(new long[]{0, 1}, t -> new long[]{t[1], t[0] + t[1]})
				.limit(100)
				.map(t -> t[0])
				.mapToLong(Long::intValue)
				.sum();

		System.out.println("Total : " + sum);


	}

	public static  Long fib(int n) {
		if(n==0){
			return 0L;
		}
		if(n==1){
			return 1L;
		}
		if(n==2){
			return 1L;
		}
		return fib(n-1)+fib(n-2);
	}



}

