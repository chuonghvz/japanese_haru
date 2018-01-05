package prm3101.group_assignment.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ai.api.AIDataService;
import ai.api.AIListener;
import ai.api.AIServiceException;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import ai.api.model.Result;
import prm3101.group_assignment.R;
import prm3101.group_assignment.adapter.ChatbotAdapter;
import prm3101.group_assignment.data.ChatMessage;
import prm3101.group_assignment.util.Utils;


public class ChatbotActivity extends AppCompatActivity implements AIListener {

    private final String TOKEN = "e5e179ae84db475facaf659427ce5904";
    private RecyclerView recyclerView;
    private Utils utils = new Utils();
    private EditText mInputText;
    private RelativeLayout voiceBtn;
    private DatabaseReference ref;
    private FirebaseRecyclerAdapter<ChatMessage, ChatbotAdapter> chatbotAdapter;
    private Boolean flagFab = true;
    private AIService aiService;

    final AIConfiguration config = new AIConfiguration(TOKEN, AIConfiguration.SupportedLanguages.English,
            AIConfiguration.RecognitionEngine.System);
    final AIDataService aiDataService = new AIDataService(config);
    final AIRequest aiRequest = new AIRequest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);

        //Set toolbar
        TextView mToolbarText = (TextView) findViewById(R.id.toolbar_text);
        mToolbarText.setText(R.string.chatbot);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mInputText = (EditText) findViewById(R.id.inputText);
        voiceBtn = (RelativeLayout) findViewById(R.id.voiceBtn);
        recyclerView = (RecyclerView) findViewById(R.id.chatData);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        //ChatBot - Register to Dialogflow/Firebase
        ActivityCompat.requestPermissions
                (this, new String[]{android.Manifest.permission.RECORD_AUDIO}, 1);
        ref = FirebaseDatabase.getInstance().getReference();
        ref.keepSynced(true);
        aiService = AIService.getService(this, config);
        aiService.setListener(this);

        // Voice/Sent button click
        voiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = mInputText.getText().toString().trim();
                if (!message.equals("")) {
                    ChatMessage chatMessage = new ChatMessage(message, "user");
                    ref.child("chat").push().setValue(chatMessage);
                    aiRequest.setQuery(message);
                    // Chat Task
                    new ChatTask().execute(aiRequest);
                } else {
                    aiService.startListening();
                }
                mInputText.setText("");
                InputMethodManager in = (InputMethodManager) getSystemService(SearchActivity.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(mInputText.getWindowToken(), 0);
            }
        });

        // Input EditText click
        mInputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                ImageView fab_img = (ImageView) findViewById(R.id.fab_img);
                Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.ic_send_white_24dp);
                Bitmap img1 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_mic_white_24dp);

                if (s.toString().trim().length() != 0 && flagFab) {
                    ImageViewAnimatedChange(ChatbotActivity.this, fab_img, img);
                    flagFab = false;
                } else if (s.toString().trim().length() == 0) {
                    ImageViewAnimatedChange(ChatbotActivity.this, fab_img, img1);
                    flagFab = true;
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Chatbot Adapter
        chatbotAdapter = new FirebaseRecyclerAdapter<ChatMessage, ChatbotAdapter>
                (ChatMessage.class, R.layout.items_chatbot, ChatbotAdapter.class, ref.child("chat")) {

            @Override
            protected void populateViewHolder(ChatbotAdapter viewHolder, ChatMessage model, int position) {

                if (model.getMsgUser().equals("user")) {
                    viewHolder.rightText.setText(model.getMsgText());
                    viewHolder.rightText.setVisibility(View.VISIBLE);
                    viewHolder.leftText.setVisibility(View.GONE);
                } else {
                    viewHolder.leftText.setText(model.getMsgText());
                    viewHolder.rightText.setVisibility(View.GONE);
                    viewHolder.leftText.setVisibility(View.VISIBLE);
                }

            }
        };

        chatbotAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int msgCount = chatbotAdapter.getItemCount();
                int lastVisiblePosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                Log.e("aaa", " " + lastVisiblePosition);
                if (lastVisiblePosition == -1 ||
                        (positionStart >= (msgCount - 1) &&
                                lastVisiblePosition == (positionStart - 1))) {
                    recyclerView.scrollToPosition(positionStart);
                }

            }
        });

        recyclerView.setAdapter(chatbotAdapter);

    }

    // Call text response from API
    public class ChatTask extends AsyncTask<AIRequest, Void, AIResponse> {
        @Override
        protected AIResponse doInBackground(AIRequest... aiRequests) {
            final AIRequest request = aiRequests[0];
            try {
                final AIResponse response = aiDataService.request(aiRequest);
                return response;
            } catch (AIServiceException e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(AIResponse response) {
            super.onPostExecute(response);
            if (response != null) {
                Result result = response.getResult();
                String reply = result.getFulfillment().getSpeech();
                ChatMessage chatMessage = new ChatMessage(reply, "bot");
                ref.child("chat").push().setValue(chatMessage);
            }
        }
    }

    // Change icon when input text
    public void ImageViewAnimatedChange(Context c, final ImageView v, final Bitmap new_image) {
        final Animation anim_out = AnimationUtils.loadAnimation(c, R.anim.zoom_out);
        final Animation anim_in = AnimationUtils.loadAnimation(c, R.anim.zoom_in);
        anim_out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.setImageBitmap(new_image);
                anim_in.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                    }
                });
                v.startAnimation(anim_in);
            }
        });
        v.startAnimation(anim_out);
    }

    // Get message result from API
    @Override
    public void onResult(AIResponse response) {
        Result result = response.getResult();
        String message = result.getResolvedQuery();
        ChatMessage chatMessage0 = new ChatMessage(message, "user");
        ref.child("chat").push().setValue(chatMessage0);
        String reply = result.getFulfillment().getSpeech();
        ChatMessage chatMessage = new ChatMessage(reply, "bot");
        ref.child("chat").push().setValue(chatMessage);
    }

    @Override
    public void onError(AIError error) {

    }

    @Override
    public void onAudioLevel(float level) {

    }

    @Override
    public void onListeningStarted() {

    }

    @Override
    public void onListeningCanceled() {

    }

    @Override
    public void onListeningFinished() {

    }

    // Back button
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
