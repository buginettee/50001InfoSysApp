package com.example.app50001;

/*
this will be a dud class to store code you need to add things nicely to the database

How to use:

Just copy and paste the code into which activity file that will be executed and change the content you want
to have pre-added into the database


you need an instance of both DatabaseReference and FirebaseAuth

Code starts:

    private DatabaseReference dbreference;
    private FirebaseAuth dbauth;


    //for box and pure user
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get auth from firebase
        dbauth = FirebaseAuth.getInstance();

        //get the current user and its unique ID
        FirebaseUser currentuser = dbauth.getCurrentUser();
        currentUID = currentuser.getUid();

        //get instance of the database
        dbreference = FirebaseDatabase.getInstance().getReference();


        //database update start!!


        Profiles newProfiles = new Profiles();
        newProfiles.setDisplayName("Jeslyn");
        newProfiles.setCompany("SUTD");
        newProfiles.setDUID("1003841");
        newProfiles.setEmail("jeslyn_peh@mymail.sutd.edu.sg");

        HashMap<String, Object> AdminOf = new HashMap<>();
        AdminOf.put("Box32","Blk57 SUTD");
        newProfiles.setAdminOf(AdminOf);

        HashMap<String, Object> GuestOf = new HashMap<>();
        GuestOf.put("Box55", "Blk59 SUTD");
        newProfiles.setGuestOf(GuestOf);

        HashMap<String, Object> DeliveryOf = new HashMap<>();
        newProfiles.setDeliveryOf(DeliveryOf);

        HashMap<String, Object> boxaccessed = new HashMap<>();
        boxaccessed.put("Box32", "Blk57");
        LocalDate date = java.time.LocalDate.now();
        String datenow = date.toString();
        HashMap<String, Object> UserHistoryOfProfile = new HashMap<>();
        UserHistoryOfProfile.put(datenow, boxaccessed);

        HashMap<String, Object> DeliveryHistoryOfProfile = new HashMap<>();
        newProfiles.setDeliveryHistoryOfProfile(DeliveryHistoryOfProfile);

        dbreference.child("Profiles").child(currentUID).setValue(newProfiles);

        Boxes newbox = new Boxes();
        newbox.setAddress("Blk57");
        newbox.setLockState("locked");
        newbox.setDoorState("locked");
        newbox.setButtonState("Locked");
        newbox.setAdditionalInstructions("Fly");
        newbox.setContact("1234 5678");

        HashMap<String, Object> AdminAccess = new HashMap<>();
        AdminAccess.put("Jeslyn", currentUID);
        newbox.setAdminAccess(AdminAccess);

        HashMap<String, Object> GuestAccess = new HashMap<>();
        GuestAccess.put("Jeslyn2", currentUID);
        newbox.setGuestAccess(GuestAccess);

        HashMap<String, Object> DeliveryAccess = new HashMap<>();
        newbox.setDeliveryAccess(DeliveryAccess);

        dbreference.child("Boxes").child("Box32").setValue(newbox);



        //for mixed
            @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get auth from firebase
        dbauth = FirebaseAuth.getInstance();

        //get the current user and its unique ID
        FirebaseUser currentuser = dbauth.getCurrentUser();
        currentUID = currentuser.getUid();

        //get instance of the database
        dbreference = FirebaseDatabase.getInstance().getReference();


        //database update start!!


        Profiles newProfiles = new Profiles();
        newProfiles.setDisplayName("NotJeslyn");
        newProfiles.setCompany("SUTD");
        newProfiles.setDUID("1004138");
        newProfiles.setEmail("jeslyn_peh@mymail.sutd.edu.sg");

        HashMap<String, Object> AdminOf = new HashMap<>();
        AdminOf.put("Box58","Blk57 SUTD");
        newProfiles.setAdminOf(AdminOf);

        HashMap<String, Object> GuestOf = new HashMap<>();
        GuestOf.put("Box32", "Blk59 SUTD");
        newProfiles.setGuestOf(GuestOf);

        HashMap<String, Object> DeliveryOf = new HashMap<>();
        DeliveryOf.put("Box32", "Blk57");
        newProfiles.setDeliveryOf(DeliveryOf);

        HashMap<String, Object> boxaccessed = new HashMap<>();
        boxaccessed.put("Box32", "Blk57");
        LocalDate date = java.time.LocalDate.now();
        String datenow = date.toString();

        HashMap<String, Object> UserHistoryOfProfile = new HashMap<>();
        UserHistoryOfProfile.put(datenow, boxaccessed);

        HashMap<String, Object> DeliveryHistoryOfProfile = new HashMap<>();
        newProfiles.setDeliveryHistoryOfProfile(DeliveryHistoryOfProfile);

        dbreference.child("Profiles").child(currentUID).setValue(newProfiles);

        Boxes newbox = new Boxes();
        newbox.setAddress("Blk57");
        newbox.setLockState("locked");
        newbox.setDoorState("locked");
        newbox.setButtonState("Locked");
        newbox.setAdditionalInstructions("Fly");
        newbox.setContact("1234 5678");

        HashMap<String, Object> AdminAccess = new HashMap<>();
        AdminAccess.put("NotJeslyn", currentUID);
        newbox.setAdminAccess(AdminAccess);

        HashMap<String, Object> GuestAccess = new HashMap<>();
        GuestAccess.put("Jeslyn8", currentUID);
        newbox.setGuestAccess(GuestAccess);

        HashMap<String, Object> DeliveryAccess = new HashMap<>();
        newbox.setDeliveryAccess(DeliveryAccess);

        dbreference.child("Boxes").child("Box58").setValue(newbox);




 */