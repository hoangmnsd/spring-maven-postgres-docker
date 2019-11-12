package com.example.springpostgresdocker.product;

//import jdk.nashorn.internal.objects.annotations.Getter;
//import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class PersistantProduct extends AbstractPersistable<Long> implements Product {
	private String name;

	public PersistantProduct() {
	}

	public PersistantProduct(String name) {
		this.name = name;
	}

	@Override public String name() {
		return name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
