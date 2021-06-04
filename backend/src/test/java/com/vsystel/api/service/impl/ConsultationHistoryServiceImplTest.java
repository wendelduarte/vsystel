package com.vsystel.api.service.impl;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;

import com.vsystel.api.models.PriceConsultResult;
import com.vsystel.api.repository.ConsultationHistoryRepository;

@ContextConfiguration(classes = { ConsultationHistoryServiceImpl.class })
@ExtendWith(MockitoExtension.class)
public class ConsultationHistoryServiceImplTest {
	
	private static final Integer PAGE = 1;
	
	@Mock
	private ConsultationHistoryRepository consultationHistoryRepository;
	
	@Mock
	private PriceConsultResult priceConsultResult;
	
	@Mock
	private Pageable pageable;
	
	@InjectMocks
	private ConsultationHistoryServiceImpl consultationHistoryService;
	
	private List<PriceConsultResult> emptyListOfConsultationHistory = new ArrayList<>();
	private List<PriceConsultResult> listOfConsultationHistory = asList(priceConsultResult);
	private Page<PriceConsultResult> historyPage = new PageImpl<>(listOfConsultationHistory);
	private Page<PriceConsultResult> emptyHistoryPage = new PageImpl<>(emptyListOfConsultationHistory);
	
	@Test
	public void givenHasHistoryOnDatabaseThenReturnEmptyList() {
		when(consultationHistoryRepository.findAll(any(Pageable.class))).thenReturn(historyPage);
		
		List<PriceConsultResult> methodResult = consultationHistoryService.getHistoryPageable(PAGE);
		
		assertFalse(methodResult.isEmpty());
		assertEquals(listOfConsultationHistory, methodResult);
	}
	
	@Test
	public void givenHasNoHistoryOnDatabaseThenReturnEmptyList() {
		when(consultationHistoryRepository.findAll(any(Pageable.class))).thenReturn(emptyHistoryPage);
		
		List<PriceConsultResult> methodResult = consultationHistoryService.getHistoryPageable(PAGE);
		
		assertTrue(methodResult.isEmpty());
		assertEquals(emptyListOfConsultationHistory, methodResult);
	}
	
	@Test
	public void givenAPriceConsultResultThenShouldSave() {
		consultationHistoryService.save(priceConsultResult);
		
		verify(consultationHistoryRepository).save(any(PriceConsultResult.class));
	}
}