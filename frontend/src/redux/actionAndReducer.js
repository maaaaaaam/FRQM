import { getQuote } from "../Utils/getQuoteFunctions";

export function reducer(state = {text: 'Quotes appear here', author: 'Author'}, action) {
    switch(action.type) {
        case 'QUOTE': return action.state;
        default: return state;
    }
}

export function quoteAction() {
    return async (dispatch) => {
        try {
            const res = await getQuote();
            dispatch({type: 'QUOTE', state: res});
        } catch {
            dispatch({type: 'QUOTE', state: {text: 'err', author: 'err'}})
        }
    }
}