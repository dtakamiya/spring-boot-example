package com.example.demo;

// DDD構成例:
// com.example.demo.domain         ... ドメイン層（エンティティ、リポジトリインターフェース等）
// com.example.demo.application    ... アプリケーション層（ユースケースサービス等）
// com.example.demo.infrastructure ... インフラ層（myBatis実装、外部APIクライアント等）
// com.example.demo.presentation   ... プレゼンテーション層（RestController等）

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demo.infrastructure")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
