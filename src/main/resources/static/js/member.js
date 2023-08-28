async function isMemberExistByID(id)
{
    const result = await axios.get(`/memberRest/memberID/${id}`)
    return result.data
}

async function isMemberExistByNickname(name)
{
    const result = await axios.get(`/memberRest/nickname/${name}`)
    return result.data
}