package com.zeus.bookcase.app.cabinet.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;
import com.zeus.bookcase.app.cabinet.adapter.MyBooksAdapter;
import com.zeus.bookcase.app.cabinet.model.Model;
import com.zeus.bookcase.app.cabinet.view.SlidingDeck;

/**
 * Created by zeus_coder on 2016/3/15.
 */
public class ChooseMyBookActivity extends BaseActivity {

    private SlidingDeck slidingDeck;
    private MyBooksAdapter booksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.case__activity_book_library);
        initTopButton(R.string.activity_charge, R.mipmap.app__top_bar_arrow_back, 0);

        initializeSlidingDeck();
    }

    private void initializeSlidingDeck() {
        booksAdapter = new MyBooksAdapter(this);
        booksAdapter.add(new Model("Emilia Clarke",
                "http://www.hollywoodreporter.com/sites/default/files/custom/Kimberly/thr_emilia_clarke.jpg",
                "Emilia Clarke is an English actress. She is best known for her role as Daenerys Targaryen in the HBO series Game of Thrones, for which she received two Emmy Award nominations for Outstanding Supporting Actress in a Drama Series in 2013 and 2015."));
        booksAdapter.add(new Model("Kit Harington",
                "http://www.ew.com/sites/default/files/styles/tout_image_612x380/public/i/2013/09/24/kit-harington-jon-hamm_612x380_0.jpg",
                "Christopher Catesby Harington, better known as Kit Harington, is an English actor. He rose to fame playing the role of Jon Snow in the television series Game of Thrones."));
        booksAdapter.add(new Model("Peter Dinklage",
                "http://media1.popsugar-assets.com/files/2015/01/05/110/n/1922398/2ce86fc8b47262e1_thumb_temp_image881416727523.xxlarge.jpg",
                "Peter Hayden Dinklage is an American actor. Since his breakout role in The Station Agent, he has appeared in numerous films and voiced Ghost in the video game, Destiny."));
        booksAdapter.add(new Model("Lena Headey",
                "http://img2.wikia.nocookie.net/__cb20150623161517/disney/images/5/5d/Lena_headey_.jpg",
                "Lena Headey is an English actress. After being scouted at age 17, Headey worked steadily as an actress in small and supporting roles in films throughout the 1990s, before finding fame for her lead"));
        booksAdapter.add(new Model("Maisie Williams",
                "http://www.ew.com/sites/default/files/styles/tout_image_612x380/public/i/2015/06/16/maisie-williams.jpg?itok=6lBByS7D",
                "Maisie Williams is an English actress. She is known for her role as Arya Stark in the HBO television series Game of Thrones, which earned her the 2012 Portal Awards for Best Supporting Actress "));
        booksAdapter.add(new Model("Sophie Turner",
                "http://static.independent.co.uk/s3fs-public/thumbnails/image/2015/02/15/10/Sophie-Turner-v2.jpg",
                "Sophie Turner is an English actress. Turner is best known for her role as Sansa Stark on the HBO fantasy television series Game of Thrones, which earned her a Young Artist Award nomination for Best Supporting Young Actress in a TV Series. "));
        booksAdapter.add(new Model("Natalie Dormer",
                "http://media1.popsugar-assets.com/files/2014/11/19/077/n/1922398/17fe79cd7add5e92_thumb_temp_image10906271416444282/i/Natalie-Dormer-Interview-Mockingjay-Part-1.jpg",
                "Natalie Dormer is an English actress. She is best known for her roles as Anne Boleyn on the Showtime series The Tudors, as Margaery Tyrell on the HBO series Game of Thrones, Irene Adler and Jamie Moriarty"));
        slidingDeck = (SlidingDeck)findViewById(R.id.my_books);
        slidingDeck.setAdapter(booksAdapter);
        slidingDeck.setEmptyView(findViewById(R.id.emptyView));
        slidingDeck.setSwipeEventListener(new SlidingDeck.SwipeEventListener() {
            @Override
            public void onSwipe(SlidingDeck parent, View item) {
                Model model = (Model) item.getTag();
                booksAdapter.remove(model);
                booksAdapter.insert(model, booksAdapter.getCount());
                booksAdapter.notifyDataSetChanged();
            }
        });
    }
}
