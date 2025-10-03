package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.db.model.PerformanceParticipant;
import lombok.Data;

@Data
public class PerformanceDto {
	    private Long performanceId;
	    private Long organizer;
	    private String title;
	    private String songList;
	    private String promoUrl;
	    private LocalDateTime performanceDatetime;
	    private String requiredPositions;
	    private String status;
	    private String chatUrl;
	    private List<PerformanceParticipantDto> participants;
}
