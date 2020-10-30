package com.nomi.rsixports.utility;

import com.nomi.rsixports.model.ProductModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FakeData {

    public static void fakeBadminton() {
        ProductModel data = new ProductModel();
        data.setCategory_uid("m6JQuvmUKE9YwHUtkckk");
        data.setName("YONEX ARCSABER 9");
        data.setBrand("Yonex");
        data.setPrice(130.0);
        data.setDescription("-Full Carbon\n" +
                "-Flexible\n" +
                "-Material-\"Fiber\"");
        data.setImages(Collections.singletonList("https://firebasestorage.googleapis.com/v0/b/r-six-sports.appspot.com/o/product%2FYONEX%20ARCSABER%209.jpeg?alt=media&token=dfb323e9-7ba5-4838-a655-822effde4915"));

        Shared.getProductCollectionReference().add(data);

        data.setCategory_uid("m6JQuvmUKE9YwHUtkckk");
        data.setName("FLEET HYPE NANO 990");
        data.setBrand("Yonex");
        data.setPrice(120.0);
        data.setDescription("-Flexible\n" +
                "-Full light weight\n" +
                "-Material-\"Hi Modulus Graphite\"");
        data.setImages(Collections.singletonList("https://firebasestorage.googleapis.com/v0/b/r-six-sports.appspot.com/o/product%2FFLEET%20HYPE%20NANO%20990.jpeg?alt=media&token=d10dfee5-f492-4605-9a7e-0d2de54fb81d"));

        Shared.getProductCollectionReference().add(data);

        data.setCategory_uid("m6JQuvmUKE9YwHUtkckk");
        data.setName("OMEGA GOLD SHUTTLECOCK");
        data.setBrand("RCL");
        data.setPrice(70.0);
        data.setDescription("Durability: Good\nFlight: Excellent\nLevel: For Advanced & Pro Players");
        data.setImages(Collections.singletonList("https://firebasestorage.googleapis.com/v0/b/r-six-sports.appspot.com/o/product%2FOMEGA%20GOLD%20SHUTTLECOCK.jpeg?alt=media&token=2e7adcd9-52d4-45be-a9ce-a415e3fb4236"));

        Shared.getProductCollectionReference().add(data);

        data.setCategory_uid("m6JQuvmUKE9YwHUtkckk");
        data.setName("PLATINUM GOLD SHUTTLECOCKS");
        data.setBrand("RCL");
        data.setPrice(70.0);
        data.setDescription("Durability: Good\nFlight: Good\nLevel: Pro Players");
        data.setImages(Collections.singletonList("https://firebasestorage.googleapis.com/v0/b/r-six-sports.appspot.com/o/product%2FPLATINUM%20GOLD%20SHUTTLECOCK.jpeg?alt=media&token=28c94cbe-b918-4f7b-a7b8-fbbe3f3f0f90"));

        Shared.getProductCollectionReference().add(data);

        data.setCategory_uid("m6JQuvmUKE9YwHUtkckk");
        data.setName("YONEX NET BN152-C PRO");
        data.setBrand("Yonex");
        data.setPrice(120.0);
        data.setDescription("-Size:6.10m x 0.76m x 1.9cm\n-Material: 18 Ply (Brown) Nylon\n-Upper Cable:3.5mm Steel\n-Upper Belt:3.75cm Cloth");
        data.setImages(Collections.singletonList("https://firebasestorage.googleapis.com/v0/b/r-six-sports.appspot.com/o/product%2FYONEX%20NET%20BN152-C%20PRO.jpeg?alt=media&token=24aa97fd-b337-4380-adc9-63f0511f037f"));

        Shared.getProductCollectionReference().add(data);

        data.setCategory_uid("m6JQuvmUKE9YwHUtkckk");
        data.setName("YONEX GRIP");
        data.setBrand("Yonex");
        data.setPrice(6.0);
        data.setDescription("Color variety");
        data.setImages(Collections.singletonList("https://firebasestorage.googleapis.com/v0/b/r-six-sports.appspot.com/o/product%2FYONEX%20GRIP.jpeg?alt=media&token=d0af4ea9-017d-4f31-9910-ebe3ed93147d"));
        List<String> choices = new ArrayList<>();
        choices.add("red");
        choices.add("blue");
        choices.add("yellow");
        choices.add("black");
        choices.add("orange");
        choices.add("white");
        choices.add("purple");
        data.setChoices(choices);

        Shared.getProductCollectionReference().add(data);
    }

    public static void fakeTakraw() {
        ProductModel data = new ProductModel();
        data.setCategory_uid("tAQi6L4oSaZggNmyzQXI");
        data.setName("BOLA TAKRAW MARATHON");
        data.setBrand("Marathon");
        data.setPrice(20.0);
        data.setDescription("\n-Made in Thailand \n-Synthetic takraw ball");
        data.setImages(Collections.singletonList("https://firebasestorage.googleapis.com/v0/b/r-six-sports.appspot.com/o/product%2FBOLA%20TAKRAW%20MARATHON.jpeg?alt=media&token=554ea11c-e8da-44ee-a322-8186493d00ac"));

        Shared.getProductCollectionReference().add(data);

        data.setCategory_uid("tAQi6L4oSaZggNmyzQXI");
        data.setName("NET TAKRAW TN80A AERO");
        data.setBrand("Aero");
        data.setPrice(40.0);
        data.setDescription("\n-Model:TN80A    \n-Standard Size   \n-Best Quality   \n-Strong nylon tying ropes");
        data.setImages(Collections.singletonList("https://firebasestorage.googleapis.com/v0/b/r-six-sports.appspot.com/o/product%2FNET%20TAKRAW%20TN80A%20AERO.jpeg?alt=media&token=ffd04516-a77a-49fa-ac48-9ea5672c054c"));

        Shared.getProductCollectionReference().add(data);
    }

    public static void fakeBola() {
        ProductModel data = new ProductModel();
        data.setCategory_uid("ZXcPffHtf0xgbTN0u7Ev");
        data.setName("BOLA ADIDAS JABULAN");
        data.setBrand("Adidas");
        data.setPrice(110.0);
        data.setDescription("-Size 5");
        data.setImages(Collections.singletonList("https://firebasestorage.googleapis.com/v0/b/r-six-sports.appspot.com/o/product%2FBOLA%20SEPAK%20ADIDAS%20JABULANI.jpeg?alt=media&token=9c982e00-0142-423e-8708-ab02970634ae"));

        Shared.getProductCollectionReference().add(data);

        data.setCategory_uid("ZXcPffHtf0xgbTN0u7Ev");
        data.setName("BOLA SEPAK UNIFORIA");
        data.setBrand("Uniforia");
        data.setPrice(150.0);
        data.setDescription("-Size 5");
        data.setImages(Collections.singletonList("https://firebasestorage.googleapis.com/v0/b/r-six-sports.appspot.com/o/product%2FBOLA%20SEPAK%20UNIFORIA.jpeg?alt=media&token=3fe71dd9-1504-42b6-b661-362c2a2cbed2"));

        Shared.getProductCollectionReference().add(data);

        data.setCategory_uid("ZXcPffHtf0xgbTN0u7Ev");
        data.setName("NET BOLA SEPAK 2.5MM");
        data.setBrand("-");
        data.setPrice(200.0);
        data.setDescription("-Size: 24' (Width) x 8' (Length)   \n-Good Quality");
        data.setImages(Collections.singletonList("https://firebasestorage.googleapis.com/v0/b/r-six-sports.appspot.com/o/product%2FNET%20BOLA%20SEPAK.jpeg?alt=media&token=5833714c-5994-43ed-bad6-348b7704f434"));

        Shared.getProductCollectionReference().add(data);

        data.setCategory_uid("QpJlUNnspyOuqHBoP2Sv");
        data.setName("KON BOLA SEPAK");
        data.setBrand("-");
        data.setPrice(6.0);
        data.setDescription("color random");
        data.setImages(Collections.singletonList("https://firebasestorage.googleapis.com/v0/b/r-six-sports.appspot.com/o/product%2FKON%20BOLA%20SEPAK.jpeg?alt=media&token=ed9a6683-ee55-426e-a611-905377a971ef"));

        Shared.getProductCollectionReference().add(data);
    }

    public static void fakeHoki() {
        ProductModel data = new ProductModel();
        data.setCategory_uid("QpJlUNnspyOuqHBoP2Sv");
        data.setName("KAYU HOKI KX300");
        data.setBrand("Kuns");
        data.setPrice(240.0);
        data.setDescription("Material:50% Carbon");
        data.setImages(Collections.singletonList("https://firebasestorage.googleapis.com/v0/b/r-six-sports.appspot.com/o/product%2FKAYU%20HOKI%20KX300.jpeg?alt=media&token=0438be92-f1cd-40bf-853e-29c0acebed88"));

        Shared.getProductCollectionReference().add(data);

        data.setCategory_uid("QpJlUNnspyOuqHBoP2Sv");
        data.setName("HELMET HOKI");
        data.setBrand("CCM");
        data.setPrice(230.0);
        data.setDescription("Tahan lasak");
        data.setImages(Collections.singletonList("https://firebasestorage.googleapis.com/v0/b/r-six-sports.appspot.com/o/product%2FHELMET%20HOKI.jpeg?alt=media&token=62e7c577-52d2-4e17-8b24-0931bb3c9315"));

        Shared.getProductCollectionReference().add(data);

        data.setCategory_uid("QpJlUNnspyOuqHBoP2Sv");
        data.setName("BOLA HOKI");
        data.setBrand("Trident");
        data.setPrice(13.0);
        data.setDescription("-1cm thickness structure");

        List<String> image = new ArrayList<>();
        image.add("https://firebasestorage.googleapis.com/v0/b/r-six-sports.appspot.com/o/product%2FBOLA%20HOKI%20TRIDENT%20(1).jpeg?alt=media&token=8e1e5bcd-d8ce-487f-acdc-4c2648415d6e");
        image.add("https://firebasestorage.googleapis.com/v0/b/r-six-sports.appspot.com/o/product%2FBOLA%20HOKI%20TRIDENT%20(2).jpeg?alt=media&token=cb152885-890a-41f5-bba4-8020d8e9246d");
        image.add("https://firebasestorage.googleapis.com/v0/b/r-six-sports.appspot.com/o/product%2FBOLA%20HOKI%20TRIDENT%20(3).jpeg?alt=media&token=437a6fc0-1711-4449-acdf-72260237a91b");
        List<String> choices = new ArrayList<>();
        choices.add("orange");
        choices.add("kuning");
        choices.add("putih");

        data.setImages(image);
        data.setChoices(choices);

        Shared.getProductCollectionReference().add(data);
    }

    public static void fakeBasketball() {
        ProductModel data = new ProductModel();
        data.setCategory_uid("m9T03RVlWKW10pq03XLi");
        data.setName("MOLTEN BASKETBALL GG7X");
        data.setBrand("Molten");
        data.setPrice(50.0);
        data.setDescription("-Size 7  \n-Material: Premium Composite Leather");
        data.setImages(Collections.singletonList("https://firebasestorage.googleapis.com/v0/b/r-six-sports.appspot.com/o/product%2FMOLTEN%20BASKETBAL.jpeg?alt=media&token=475c86a5-dadf-4fa4-97cb-257dca5e2e13"));

        Shared.getProductCollectionReference().add(data);

        data.setCategory_uid("m9T03RVlWKW10pq03XLi");
        data.setName("SPALDING NBA SILVER SERIES BASKETBALL");
        data.setBrand("Spalding");
        data.setPrice(75.0);
        data.setDescription("-Size:7   \n-Item:83-494Z");
        data.setImages(Collections.singletonList("https://firebasestorage.googleapis.com/v0/b/r-six-sports.appspot.com/o/product%2FSPALDING%20NBA%20BASKETBALL.jpeg?alt=media&token=85782b80-0ac5-43e4-8ea6-1d7adc5ee8bb"));

        Shared.getProductCollectionReference().add(data);

        data.setCategory_uid("m9T03RVlWKW10pq03XLi");
        data.setName("NET BASKETBALL");
        data.setBrand("-");
        data.setPrice(20.0);
        data.setDescription("-High quality");
        data.setImages(Collections.singletonList("https://firebasestorage.googleapis.com/v0/b/r-six-sports.appspot.com/o/product%2FNET%20BASKETBALL.jpeg?alt=media&token=1898ef22-0c9e-4aeb-8050-a96231727d1f"));

        Shared.getProductCollectionReference().add(data);
    }

    public static void fakePingpong() {
        ProductModel data = new ProductModel();
        data.setCategory_uid("M6lQCcHeoqeYrkcu319I");
        data.setName("BOLA PINGPONG");
        data.setBrand("-");
        data.setPrice(12.0);
        data.setDescription("-1 set: 10pcs  \n-Quality:Good   \n-Material:Canvas");
        data.setImages(Collections.singletonList("https://firebasestorage.googleapis.com/v0/b/r-six-sports.appspot.com/o/product%2FBOLA%20PINGPONG.jpeg?alt=media&token=7d7ff90c-675d-42c0-b439-76089d7d6f52"));

        Shared.getProductCollectionReference().add(data);

        data.setCategory_uid("M6lQCcHeoqeYrkcu319I");
        data.setName("RAKET PINGPONG");
        data.setBrand("Crossway");
        data.setPrice(30.0);
        data.setDescription("-1 set:2pcs   \n-Weight:600g   \n-Material:Wood");
        data.setImages(Collections.singletonList("https://firebasestorage.googleapis.com/v0/b/r-six-sports.appspot.com/o/product%2FRAKET%20PINGPONG.jpeg?alt=media&token=ca81bdd1-06f7-47e3-91b0-beff28e9c13f"));

        Shared.getProductCollectionReference().add(data);

        data.setCategory_uid("M6lQCcHeoqeYrkcu319I");
        data.setName("MEJA PINGPONG");
        data.setBrand("Tibhar");
        data.setPrice(750.0);
        data.setDescription("\n-Free Net  \n-Board Thickness:18mm");
        data.setImages(Collections.singletonList("https://firebasestorage.googleapis.com/v0/b/r-six-sports.appspot.com/o/product%2FMEJA%20PINGPONG.jpeg?alt=media&token=679a3f7e-fe3b-48bd-b268-9c0d2ae67316"));

        Shared.getProductCollectionReference().add(data);
    }
}
