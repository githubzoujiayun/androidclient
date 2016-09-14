/*
 * Kontalk Android client
 * Copyright (C) 2016 Kontalk Devteam <devteam@kontalk.org>

 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.kontalk.ui.view;

import java.util.HashMap;
import java.util.Map;

import org.kontalk.R;


/**
 * Factory to build balloon themes.
 * @author Daniele Ricci
 */
public class MessageListItemThemeFactory {

    private interface FactoryCreator {
        MessageListItemTheme create(int direction);
    }

    private static final Map<String, FactoryCreator> mThemes = new HashMap<>();

    static {
        mThemes.put("hangout", new FactoryCreator() {
            @Override
            public MessageListItemTheme create(int direction) {
                return new HangoutMessageTheme(direction);
            }
        });
        mThemes.put("classic", new FactoryCreator() {
            @Override
            public MessageListItemTheme create(int direction) {
                return new SimpleMessageTheme(R.drawable.balloon_classic_incoming,
                    R.drawable.balloon_classic_outgoing);
            }
        });
        mThemes.put("old_classic", new FactoryCreator() {
            @Override
            public MessageListItemTheme create(int direction) {
                return new SimpleMessageTheme(R.drawable.balloon_old_classic_incoming,
                    R.drawable.balloon_old_classic_outgoing);
            }
        });
        mThemes.put("iphone", new FactoryCreator() {
            @Override
            public MessageListItemTheme create(int direction) {
                return new SimpleMessageTheme(R.drawable.balloon_iphone_incoming,
                    R.drawable.balloon_iphone_outgoing);
            }
        });
    }

    private MessageListItemThemeFactory() {
    }

    public static MessageListItemTheme createTheme(String theme, int direction, boolean event) {
        if (event) {
            return new EventMessageTheme(R.layout.balloon_event);
        }
        else {
            FactoryCreator factory = mThemes.get(theme);
            if (factory == null)
                throw new IllegalArgumentException("theme not found: " + theme);

            return factory.create(direction);
        }
    }
}
