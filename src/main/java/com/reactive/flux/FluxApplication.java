package com.reactive.flux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Mono;


@FunctionalInterface
interface Predicate<String> {
	boolean check(String string);
}
public class FluxApplication {

	public static void main(String[] args) {

		System.out.println("Hellghjfhfgo");

		Predicate<String> myPredicate = (string) -> string.startsWith("S");

		Mono.just("Santiago").log().subscribe(person -> System.out.println(person.startsWith("F")));
	}



}

