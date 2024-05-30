package com.example.demo.Repository;
import com.example.demo.Model.Complaints;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ComplaintsRepository extends CassandraRepository<Complaints, UUID> {
}