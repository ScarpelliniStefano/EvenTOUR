package com.scarcolo.eventour.repository.sponsorship;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.scarcolo.eventour.model.sponsorship.Sponsorship;

public interface SponsorshipRepository extends MongoRepository<Sponsorship, String> {

}
