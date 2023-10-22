import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.maxpayneman.aulayt_8.R
import com.maxpayneman.project_movie.Model.FilmeLista

class MeuAdaptador(private val context: Context, private val listaDeFilmes: List<FilmeLista>) : BaseAdapter() {

    override fun getCount(): Int {
        return listaDeFilmes.size
    }

    override fun getItem(position: Int): Any {
        return listaDeFilmes[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.item_filme, null)
        }

        val nomeFilmeTextView = view?.findViewById<TextView>(R.id.nome_filme)
        val filme = listaDeFilmes[position]
        nomeFilmeTextView?.text = filme.nome
        val urlImagem = filme.imgUrl

        val imageView = view?.findViewById<ImageView>(R.id.img_filmes)
        Glide.with(context).load(urlImagem).into(imageView!!)

        return view!!


}
}
