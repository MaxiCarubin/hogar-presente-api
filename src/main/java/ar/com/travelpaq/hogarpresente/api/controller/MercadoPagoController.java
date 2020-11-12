package ar.com.travelpaq.hogarpresente.api.controller;

import ar.com.travelpaq.hogarpresente.api.models.services.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class MercadoPagoController {
    @Autowired
    private ICursoService cursoService;
/*


    @GetMapping("/createAndRedirect")
    public ResponseEntity<?> create(@RequestBody Curso curso)throws MPException{
        Preference preference = new Preference();

        preference.setBackUrls(
                new BackUrls()
                        .setFailure("http://localhost:8080/failure")
                        .setPending("http://localhost:8080/pending")
                        .setSuccess("http://localhost:8080/success")
        );

        Item item = new Item();
        if(cursoService.findById2(curso.getId()) == true){
            item.setTitle(curso.getNombre());
            item.setQuantity(1);
            item.setDescription(curso.getDescripcion());
            item.setUnitPrice((float) curso.getPrecio());
            preference.appendItem(item);

            var result = preference.save();

            System.out.println(result.getSandboxInitPoint());
            return "redirect:" + result.getSandboxInitPoint();
        }
        else return "Error";
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
                          @RequestParam("merchant_accuont_id") String merchantAccuontId,
                          Model model
    ) throws MPException{
        var payment = com.mercadopago.resources.Payment.findById(collectionId);

        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(payment));

        model.addAttribute("payment", payment);
        return "ok";
    }

    @GetMapping("/failure")
    public String failure(HttpServletRequest request)

 */
}
