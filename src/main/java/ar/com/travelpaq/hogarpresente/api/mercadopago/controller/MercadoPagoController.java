package ar.com.travelpaq.hogarpresente.api.mercadopago.controller;

import com.google.gson.GsonBuilder;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Item;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MercadoPagoController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${mp.accesstoken}")
    private String secret;

    @GetMapping("/create")
    public void createPreference() throws MPException {

        MercadoPago.SDK.setAccessToken(secret);

        Preference preference = new Preference();

//        p.setBackUrls(
//                new BackUrls()
//                        .setSuccess("http://localhost:8080/success")
//                        .setPending("http://localhost:8080/pending")
//                        .setFailure("http://localhost:8080/failure")
//        );

        Item item = new Item();
        item.setTitle("Mi producto")
                .setQuantity(1)
                .setUnitPrice((float) 75.56);
        preference.appendItem(item);
        preference.save();

    }

    @GetMapping("/success")
    public String success(HttpServletRequest request,
                          @RequestParam("collection_id") String collectionId,
                          @RequestParam("collection_status") String collectionStatus,
                          @RequestParam("external_reference") String externalReference,
                          @RequestParam("payment_type") String paymentType,
                          @RequestParam("merchant_order_id") String merchantOrderId,
                          @RequestParam("preference_id") String preferenceId,
                          @RequestParam("site_id") String siteId,
                          @RequestParam("processing_mode") String processingMode,
                          @RequestParam("merchant_account_id") String merchantAccountId,
                          Model model
    ) throws MPException{
        var payment = com.mercadopago.resources.Payment.findById(collectionId);
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(payment));
        model.addAttribute("payment", payment);
        return "ok";
    }

}
