package com.ricardo.springboot.mapper;

import java.util.ArrayList;
import java.util.List;

import com.ricardo.springboot.data.dto.PersonDTO;
import com.ricardo.springboot.model.Person;
import org.modelmapper.ModelMapper;

public class Mapper {
	
	private static ModelMapper mapper = new ModelMapper();

	static {
		mapper.createTypeMap(Person.class, PersonDTO.class)
				.addMapping(Person::getId, PersonDTO::setKey);
		mapper.createTypeMap(PersonDTO.class, Person.class)
				.addMapping(PersonDTO::getKey, Person::setId);
	}
	
	public static <O, D> D parseObject(O origin, Class<D> destination) {
		return mapper.map(origin, destination);
	}
	
	public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
		List<D> destinationObjects = new ArrayList<D>();
		for (O o : origin) {
			destinationObjects.add(mapper.map(o, destination));
		}
		return destinationObjects;
	}

}
