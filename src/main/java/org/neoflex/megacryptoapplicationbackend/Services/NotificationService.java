package org.neoflex.megacryptoapplicationbackend.Services;

import org.neoflex.megacryptoapplicationbackend.Models.CandleDetailsDTO;
import org.neoflex.megacryptoapplicationbackend.Models.NotificationsData;
import org.neoflex.megacryptoapplicationbackend.Models.SubscribeRequestDTO;
import org.neoflex.megacryptoapplicationbackend.Persistence.Entity.CandleDetails;
import org.neoflex.megacryptoapplicationbackend.Persistence.Entity.Embeddable.CandleDetailsId;
import org.neoflex.megacryptoapplicationbackend.Persistence.Entity.Embeddable.RequestId;
import org.neoflex.megacryptoapplicationbackend.Persistence.Entity.SubscribeRequest;
import org.neoflex.megacryptoapplicationbackend.Persistence.Repository.CandleDetailsRepository;
import org.neoflex.megacryptoapplicationbackend.Persistence.Repository.SubscribeRequestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    private SubscribeRequestsRepository subscribeRequestsRepository;
    private CandleDetailsRepository candleDetailsRepository;

    @Autowired
    public NotificationService(SubscribeRequestsRepository subscribeRequestsRepository, CandleDetailsRepository candleDetailsRepository) {
        this.subscribeRequestsRepository = subscribeRequestsRepository;
        this.candleDetailsRepository = candleDetailsRepository;
    }

    public void subscribe(SubscribeRequestDTO subscribeRequestDTO, Principal principal) {
        SubscribeRequest subscribeRequest = new SubscribeRequest();
        RequestId requestId = new RequestId(principal.getName(), subscribeRequestDTO.getCurrency());
        subscribeRequest.setId(requestId);
        subscribeRequestsRepository.save(subscribeRequest);
    }

    public void unsubscribe(SubscribeRequestDTO subscribeRequestDTO, Principal principal) {
        SubscribeRequest subscribeRequest = new SubscribeRequest();
        RequestId requestId = new RequestId(principal.getName(), subscribeRequestDTO.getCurrency());
        subscribeRequest.setId(requestId);
        subscribeRequestsRepository.delete(subscribeRequest);
    }

    public NotificationsData notificationsData(Principal principal) {
        NotificationsData notificationsData = new NotificationsData();
        List<SubscribeRequest> subscribeRequests = subscribeRequestsRepository.findAllByIdUsername(principal.getName());
        List<String> currencies = subscribeRequests.stream().map(e -> e.getId().getCurrency()).collect(Collectors.toList());

        List<CandleDetailsDTO> candleDetailsDTOS = candleDetailsRepository.findByIdCurrencyIn(currencies).stream().map(e -> new CandleDetailsDTO(e.getId().getCurrency(), e.getLow(), e.getHigh(), e.getOpen(), e.getClose(), e.getId().getOpenTime())).collect(Collectors.toList());
        notificationsData.setCandleDetailsList(candleDetailsDTOS);
        return notificationsData;
    }


    public void addNotify(CandleDetailsDTO candleDetailsDTO) {
        List<SubscribeRequest> subscribeRequests = subscribeRequestsRepository.findAll();
        List<CandleDetails> candleDetailsList = new ArrayList<>();
        subscribeRequests.stream().filter(e -> e.getId().getCurrency().equals(candleDetailsDTO.getCurrency()))
                .forEach(e -> {
                    CandleDetailsId candleDetailsId = new CandleDetailsId();
                    candleDetailsId.setCurrency(e.getId().getCurrency());
                    candleDetailsId.setOpenTime(candleDetailsDTO.getOpenTime());
                    CandleDetails candleDetails = new CandleDetails(candleDetailsId, candleDetailsDTO.getLow(), candleDetailsDTO.getHigh(), candleDetailsDTO.getOpen(), candleDetailsDTO.getClose());
                    candleDetails.setId(candleDetailsId);

                    candleDetailsList.add(candleDetails);

                });

        candleDetailsRepository.saveAll(candleDetailsList);


    }


}
