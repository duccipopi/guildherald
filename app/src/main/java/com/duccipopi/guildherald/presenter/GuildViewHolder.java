package com.duccipopi.guildherald.presenter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.duccipopi.guildherald.R;
import com.duccipopi.guildherald.model.dao.Guild;
import com.duccipopi.guildherald.presenter.base.GenericViewHolder;
import com.duccipopi.guildherald.presenter.base.GenericViewHolderFactory;
import com.duccipopi.guildherald.util.Utilities;

/**
 * Created by ducci on 03/02/2018.
 */

public class GuildViewHolder extends GenericViewHolder<Guild>
        implements GenericViewHolderFactory<GuildViewHolder> {

    ImageView emblem;
    TextView guildName;
    TextView realm;
    TextView members;

    public GuildViewHolder(View view) {
        super(view);
    }

    @Override
    public void bind(Guild item) {
        emblem = itemView.findViewById(R.id.emblem);
        guildName = itemView.findViewById(R.id.guild_name);
        realm = itemView.findViewById(R.id.realm);
        members = itemView.findViewById(R.id.members);

        Utilities.Image.loadImage(itemView.getContext(),
                Utilities.Blizzard.getEmblemURL(item),
                R.drawable.default_emblem, R.drawable.error_portrait, emblem);
        guildName.setText(item.getName());
        realm.setText(item.getRealm());
        members.setText(Integer.toString(item.getNumMembers()));
    }

    @Override
    public GuildViewHolder create(View itemView) {
        return new GuildViewHolder(itemView);
    }
}
